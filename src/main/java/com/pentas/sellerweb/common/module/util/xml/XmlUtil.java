package com.pentas.sellerweb.common.module.util.xml;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.pentas.sellerweb.common.module.util.CmmnUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class XmlUtil {

	/**
	 * 클래스 객체를 xml 문자열로 변환한다.
	 * @param target xml로 변환할 클래스 객체
	 * @return
	 * @throws JsonProcessingException
	 */
	public static String toXml(Object target) throws JsonProcessingException {
		XmlMapper mapper = new XmlMapper();
		return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(target);
	}
	
	/**
	 * xml 문자열을 클래스 객체로 변환한다.
	 * @param classpath 변환할 대상 클래스의 타입
	 * @param target 변환할 xml String
	 * @return <T> T 클래스 객체
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public static <T> T fromXml(Class<T> classpath, String target) throws JsonParseException, JsonMappingException, IOException {
		XmlMapper mapper = new XmlMapper();
		return mapper.readValue(target, classpath);
	}
	
	/**
	 * 파일로부터 문서를 생성한다.
	 * @param f
	 * @param validate
	 * @return
	 */
	public static Document parseDocument(File f, boolean validate) {
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			dbf.setValidating(validate);
			dbf.setNamespaceAware(false);
			DocumentBuilder db = dbf.newDocumentBuilder();
			db.setErrorHandler(new XmlDocumentErrorHandler());
			return db.parse(f);
		} catch(SAXException ex) {
			log.error(CmmnUtil.getExceptionMsg(ex));
		} catch(ParserConfigurationException ex) {
			log.error(CmmnUtil.getExceptionMsg(ex));
		} catch(IOException ex) {
			log.error(CmmnUtil.getExceptionMsg(ex));
		}
		return null;
	}
	
	/**
	 * 파일로부터 문서를 생성한다.
	 * @param f
	 * @return
	 */
	public static Document parseDocument(File f) {
		return parseDocument(f, false);
	}
	
	/**
	 * 입력스트림으로부터 문서를 생성한다.
	 * @param in 입력스트림.
	 * @param validate
	 * @return
	 */
	public static Document parseDocument(InputStream in, boolean validate) {
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			dbf.setValidating(validate);
			dbf.setNamespaceAware(false);
			DocumentBuilder db = dbf.newDocumentBuilder();
			db.setErrorHandler(new XmlDocumentErrorHandler());
			return db.parse(in);
		} catch(SAXException ex) {
			log.error(CmmnUtil.getExceptionMsg(ex));
		} catch(ParserConfigurationException ex) {
			log.error(CmmnUtil.getExceptionMsg(ex));
		} catch(IOException ex) {
			log.error(CmmnUtil.getExceptionMsg(ex));
		}
		return null;
	}
	
	/**
	 * 입력스트림으로부터 문서를 생성한다.
	 * @param in 입력스트림.
	 * @return
	 */
	public static Document parseDocument(InputStream in) {
		return parseDocument(in, false);
	}
	
	/**
	 * xml 문자열을 Document형태로 변환한다.
	 * @param xmlString Document로 변환할 xmlString
	 * @return
	 */
	public static Document toDocument(String xmlString){
		try(StringReader sr = new StringReader(xmlString)){
			DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			return db.parse(new InputSource(sr));
		} catch(SAXException ex) {
			log.error(CmmnUtil.getExceptionMsg(ex));
		} catch(ParserConfigurationException ex) {
			log.error(CmmnUtil.getExceptionMsg(ex));
		} catch(IOException ex) {
			log.error(CmmnUtil.getExceptionMsg(ex));
		}
		return null;
	}
	
	/**
	 * 노드의 값을 반환한다.
	 * @param root 시작노드
	 * @param name '.'으로 연결된 노드명의 리스트.
	 * @return
	 */
	public static String getNodeValue(Node root, String name) {
		if(root == null || name == null) {
			return null;
		}
		Node node = root;
		NodeList list = null;
		StringTokenizer st = new StringTokenizer(name, ".");
		String s1 = null, s2 = null;
		
		for(s1 = st.nextToken(); s1 != null; s1 = s2) {
			try {
				s2 = st.nextToken();
			} catch (NoSuchElementException ex) {
				s2 = null;
			}
			if((list = node.getChildNodes()) == null) {
				return null;
			}
			int i;
			for(i = 0; i<list.getLength(); i++) {
				node = list.item(i);
				String s = node.getNodeName();
				if(s.equalsIgnoreCase(s1) == true) {
					if(s2 != null) {
						break;
					}
					Node child = node.getFirstChild();
					if(child == null) {
						return null;
					} else {
						return child.getNodeValue();
					}
				}
			}
			if(i == list.getLength()) {
				break;
			}
		}
		return null;
	}
	
	/**
	 * 주어진 노드의 속성값을 반환한다.
	 * @param root 시작노드.
	 * @param nodeName '.'으로 연결된 노드명의 리스트.
	 * @param attrName 주어진 노드의 속성명
	 * @return
	 */
	public static String getAttrValue(Node root, String nodeName, String attrName) {
		if(root == null || nodeName == null || attrName == null) {
			return null;
		}
		Node node = root;
		NodeList list = null;
		StringTokenizer st = new StringTokenizer(nodeName, ".");
		String s1 = null, s2 = null;
		
		for(s1 = st.nextToken(); s1 != null; s1 = s2) {
			try {
				s2 = st.nextToken();
			} catch (NoSuchElementException ex) {
				s2 = null;
			}
			if((list = node.getChildNodes()) == null) {
				return null;
			}
			int i;
			for(i = 0; i<list.getLength(); i++) {
				node = list.item(i);
				String s = node.getNodeName();
				if(s.equalsIgnoreCase(s1) == true) {
					if(s2 != null) {
						break;
					}
					NamedNodeMap attrs = node.getAttributes();
					for(int k = 0; k<attrs.getLength(); k++) {
						Attr attr = (Attr) attrs.item(k);
						if(attr.getName().equalsIgnoreCase(attrName) == true) {
							return attr.getValue();
						}
					}
					return null;
				}
			}
			if(i == list.getLength()) {
				break;
			}
		}
		return null;
	}
	
	/**
	 * 주어진 노드의 속성값을 반환한다.
	 * @param node 노드 객체
	 * @param attrName 속성명
	 * @return
	 */
	public static String getAttrValue(Node node, String attrName) {
		if(node == null || attrName == null) {
			return null;
		}
		NamedNodeMap attrs = node.getAttributes();
		for(int i = 0; i<attrs.getLength(); i++) {
			Attr attr = (Attr) attrs.item(i);
			if(attr.getName().equalsIgnoreCase(attrName) == true) {
				return attr.getValue();
			}
		}
		return null;
	}
	
	/**
	 * 주어진 이름의 노드를 반환한다.
	 * @param root 시작노드
	 * @param name '.'으로 연결된 노드명의 리스트
	 * @return
	 */
	public static Vector getNodes(Node root, String name) {
		if(root == null || name == null) {
			return null;
		}
		Vector data = new Vector<>();
		Node node = root;
		NodeList list = null;
		StringTokenizer st = new StringTokenizer(name, ".");
		String s, s1, s2;
		for(s1 = st.nextToken(); s1 != null; s1 = s2) {
			try {
				s2 = st.nextToken();
			} catch (NoSuchElementException ex) {
				s2 = null;
			}
			list = node.getChildNodes();
			for(int i = 0; i<list.getLength(); i++) {
				node = list.item(i);
				s = node.getNodeName();
				if(s.equalsIgnoreCase(s1) == true) {
					if(s2 != null) {
						break;
					} else {
						data.add(node);
					}
				}
			}
		}
		return data;
	}
}
