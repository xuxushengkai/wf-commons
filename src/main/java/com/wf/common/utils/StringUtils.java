package com.wf.common.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wf.common.exception.WFException;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
public class StringUtils extends org.apache.commons.lang3.StringUtils{

	public static String getObjectValue(Object obj){
		return obj==null?"":obj.toString();
	}

	public static String mapToUrl(Map<String, Object> params){
		StringBuilder url = new StringBuilder();
		params.forEach((k,v)->url.append(k+"="+v+"&"));
		String urls = url.toString();
		return urls.substring(0, urls.length()-1);
	}

	public static String parse(Object obj) throws JsonProcessingException {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		}catch (JsonProcessingException e){
			log.error("Json转化失败，原因：{}", e.getMessage());
			throw new WFException("Json转化失败", e);
		}
	}
}
