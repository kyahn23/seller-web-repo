package com.pentas.sellerweb.common.module.util;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;

import com.google.common.base.Throwables;
import com.pentas.sellerweb.common.module.ApplicationContextProvider;

public class CmmnUtil {
	
	/**
	 * DevMap 리스트를 key 값에 의거해서 정렬하는 함수
	 * @param mapList
	 * @param key 비교한 키값
	 * @param isAscent 오름차순으로 정렬여부
	 * @return
	 */
	public static List<DevMap> sortDevMapList(final List<DevMap> mapList, String key, boolean isAscent){
		Collections.sort(mapList, new Comparator<DevMap>() {
			@Override
			public int compare(DevMap map1, DevMap map2) {
				String v1 = map1.getString(key);
				String v2 = map2.getString(key);
				return ((Comparable<String>) v2).compareTo(v1);
			}
		});
		if(isAscent) Collections.reverse(mapList);
		return mapList;
	}
	
	/**
	 * response의 헤더중 Content-Disposition 지정하기
	 * @param filename
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	public static void setContectDispositionResponse(String filename, HttpServletRequest request, HttpServletResponse response) throws IOException {
		String dispositionPrefix = "attachment; filename=";
		String encodedFilename = encodeString4Browser(request, filename);
		response.setHeader("Content-Disposition", dispositionPrefix + encodedFilename);
	}
	
	/**
	 * 예외에서 상세내용을 리턴
	 * @param ex
	 * @return
	 */
	public static String getExceptionMsg(Exception ex) {
		return Throwables.getStackTraceAsString(ex);
	}
	
	/**
	 * bean명으로 bean을 가져오는 함수
	 * @param beanId
	 * @return
	 */
	public static Object getBean(String beanId) {
		ApplicationContext applicationContext = ApplicationContextProvider.getApplicationContext();
		if(applicationContext == null) {
			throw new NullPointerException("Spring의 Application이 초기화가 안됨");
		}
		return applicationContext.getBean(beanId);
	}
	
	/**
	 * 브라우저 종류 파악하기
	 * @param request
	 * @return
	 */
	public static String getBrowser(HttpServletRequest request) {
		String header = request.getHeader("User-Agent");
		if(header.contains("MSIE") || header.contains("Trident")) {
			return "MSIE";
		} else if(header.contains("Chrome")) {
			return "Chrome";
		} else if(header.contains("Opera")) {
			return "Opera";
		}
		return "Firefox";
	}
	
	public static String encodeString4Browser(HttpServletRequest request, String str) throws IOException {
		String browser = getBrowser(request);
		
		String rslt = null;
		if(browser.equals("MSIE")) {
			rslt = URLEncoder.encode(str, "UTF-8").replaceAll("\\+", "%20");
		} else if(browser.equals("Firefox")) {
			rslt = "\"" + new String(str.getBytes("UTF-8"), "8859_1") + "\"";
		} else if(browser.equals("Opera")) {
			rslt = "\"" + new String(str.getBytes("UTF-8"), "8859_1") + "\"";
		} else if(browser.equals("Chrome")) {
			StringBuffer sb = new StringBuffer();
			for(int i=0; i<str.length(); i++) {
				char c = str.charAt(i);
				if(c > '~') {
					sb.append(URLEncoder.encode("" + c, "UTF-8"));
				} else {
					sb.append(c);
				}
			}
			rslt = sb.toString();
		} else {
			throw new IOException("Not supported browser");
		}
		return rslt;
	}
	
	/**
	 * 클라이언트 아이피 주소 획득
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unused")
	private static String getIpAddr(HttpServletRequest request) {
		return StringUtils.defaultIfEmpty(request.getHeader("X-Forwarded-For"), request.getRemoteAddr());
	}

}
