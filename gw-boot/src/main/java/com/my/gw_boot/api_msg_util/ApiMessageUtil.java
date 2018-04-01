package com.my.gw_boot.api_msg_util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.my.gw_boot.model.RequestApiInfo;
import com.my.gw_boot.model.ResponseApiInfo;

public class ApiMessageUtil {

	private ObjectMapper mapper = new ObjectMapper();

	private Object jsonToObject(String json, Object object) throws JsonParseException, JsonMappingException, IOException {
		Object obj = mapper.readValue(json, object.getClass());
		return obj;
	}

	private String objectToJson(Object object) throws JsonParseException, JsonMappingException, IOException {
		return mapper.writeValueAsString(object);
	}
	
	public int getApiMessageLength(String message) throws Exception {
		int messageLen = 0;
		
		try {
			messageLen = Integer.valueOf(message.substring(0, 4));
		} catch (Exception e) {
			//json message parse error.
			throw e;
		}

		return messageLen;
	}
	
	public String getApiMessageTid(String message) throws Exception {
		String tXid = null;
		
		try {
			
			int messageLen = message == null ? 0 : Integer.valueOf(message.substring(0, 4));

			if(messageLen > 0){
				String json = message.substring(4);
				Map<String, Object> jsonMap = mapper.readValue(json, Map.class);
				tXid = (String)jsonMap.get("tXid");
			}
		} catch (Exception e) {
			//json message parse error.
			throw e;
		}

		return tXid;
	}

	public String getApiMessageType(String message) throws Exception {
		String messageType = null;
		
		try {
			int messageLen = Integer.valueOf(message.substring(0, 4));

			if(messageLen > 0){
				String json = message.substring(4);
				Map<String, Object> jsonMap = mapper.readValue(json, Map.class);
				messageType = (String)jsonMap.get("apiType");
			}
		} catch (Exception e) {
			//json message parse error.
			throw e;
		}

		return messageType;
	}
	
	public Map<Object, Object> getApiMessageData(String message) throws Exception {
		Map<Object, Object> resultMap = null;
		
		try {
			int messageLen = Integer.valueOf(message.substring(0, 4));

			if(messageLen > 0){
				String json = message.substring(4);
				Map<Object, Object> tmpMap = mapper.readValue(json, Map.class);
				resultMap = (Map<Object, Object>)mapper.convertValue(tmpMap.get("data"), Map.class);
			}
		} catch (Exception e) {
			//json message parse error.
			throw e;
		}
		
		return resultMap;
	}
	
	public Object getApiMessageObject(String message, Object object) throws Exception {
		Object resultObject = null;
		
		try {
			int messageLen = Integer.valueOf(message.substring(0, 4));

			if(messageLen > 0){
				String json = message.substring(4);
				Map<Object, Object> tmpMap = mapper.readValue(json, Map.class);
				resultObject = mapper.convertValue(tmpMap.get("data"), object.getClass());
			}
		} catch (Exception e) {
			//json message parse error.
			throw e;
		}
		
		return resultObject;
	}
	
	public RequestApiInfo makeRequestApiInfo(String apiType, Object data) throws Exception {
		RequestApiInfo result = new RequestApiInfo();
		
		try{
			result.setApiType(apiType);
			Map<Object, Object> requestMap = (Map<Object, Object>)mapper.convertValue(data, Map.class);
			result.setData(requestMap);
			result.settXid((String)requestMap.get("tXid"));
			
			Date currentDate = new Date();
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
			result.setRequestDate(df.format(currentDate));
			result.setResponseDate( df.format(currentDate) );
		} catch(Exception e){
			//json message parse error.
			throw e;
		}
		
		return result;
	}
	
	public RequestApiInfo makeRequestApiInfo(String apiType, String tXid, Object data) throws Exception {
		RequestApiInfo result = new RequestApiInfo();
		
		try{
			result.setApiType(apiType);
			result.settXid(tXid);
			Map<Object, Object> requestMap = (Map<Object, Object>)mapper.convertValue(data, Map.class);
			result.setData(requestMap);
			result.settXid((String)requestMap.get("tXid"));
			
			Date currentDate = new Date();
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
			result.setRequestDate(df.format(currentDate));
			result.setResponseDate( df.format(currentDate) );
		} catch(Exception e){
			//json message parse error.
			throw e;
		}
		
		return result;
	}
	
	public ResponseApiInfo makeResponseApiInfo(String apiType, Object data) throws Exception {
		ResponseApiInfo result = new ResponseApiInfo();
		
		try{
			result.setApiType(apiType);
			Map<Object, Object> responseMap = (Map<Object, Object>)mapper.convertValue(data, Map.class);
			result.setData(responseMap);
			result.settXid((String)responseMap.get("tXid"));
			
			Date currentDate = new Date();
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
			result.setRequestDate(df.format(currentDate));
			result.setResponseDate( df.format(currentDate) );
		} catch(Exception e){
			//json message parse error.
			throw e;
		}
		
		return result;
	}
	
	public ResponseApiInfo makeResponseApiInfoList(Object data) throws Exception {
		ResponseApiInfo result = new ResponseApiInfo();
		
		try{
			Map<Object, Object> reqestMap = (Map<Object, Object>)mapper.convertValue(data, Map.class);
			
		} catch(Exception e){
			//json message parse error.
			throw e;
		}
		
		return result;
	}

}
