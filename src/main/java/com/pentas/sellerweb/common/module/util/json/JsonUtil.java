package com.pentas.sellerweb.common.module.util.json;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pentas.sellerweb.common.module.util.CmmnUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JsonUtil {

	/**
	 * 클래스 객체를 json 문자열로 변환한다.
	 * @param target
	 * @return
	 */
	public static String toJsonStr(Object target) {
		if(target == null) return null;
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(target);
		} catch(JsonProcessingException ex) {
			log.error(CmmnUtil.getExceptionMsg(ex));
		}
		return null;
	}
	
	/**
	 * json문자열을 클래스 객체로 변환한다.
	 * @param <T>
	 * @param classpath 변환할 대상 클래스의 타입
	 * @param target 변환할 json String
	 * @return
	 */
	public static <T> T fromJsonStr(Class<T> classpath, String target) {
		try {
			if(target.equals("\"\"")) {
				return classpath.newInstance();
			}
			ObjectMapper mapper = new ObjectMapper();
			return mapper.readValue(target, classpath);
		} catch(IOException ex) {
			log.error(CmmnUtil.getExceptionMsg(ex));
		} catch(InstantiationException ex) {
			log.error(CmmnUtil.getExceptionMsg(ex));
		} catch(IllegalAccessException ex) {
			log.error(CmmnUtil.getExceptionMsg(ex));
		}
		return null;
	}
	
	/**
	 * json문자열을 jsonNode 로 변환한다.
	 * @param jsonStr json 문자열
	 * @return
	 */
	public static JsonNode getJsonNode(String jsonStr) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			return mapper.readTree(jsonStr);
		} catch (JsonProcessingException ex) {
			log.error(CmmnUtil.getExceptionMsg(ex));
		} catch (IOException ex) {
			log.error(CmmnUtil.getExceptionMsg(ex));
		}
		return null;
	}
}
