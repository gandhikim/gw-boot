package com.my.gw_boot.model;

import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RequestApiInfo {

	private String apiType;

	private String tXid;

	private String requestDate;

	private String responseDate;

	private Map<Object, Object> data;

	public String toJsonString() throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		String json =  mapper.writeValueAsString(this);
		String message = String.format("%04d", json.length());
		message += json;
		return message;
	}
	
	public String getApiType() {
		return apiType;
	}
	public void setApiType(String apiType) {
		this.apiType = apiType;
	}
	public String getRequestDate() {
		return requestDate;
	}
	public void setRequestDate(String requestDate) {
		this.requestDate = requestDate;
	}
	public String getResponseDate() {
		return responseDate;
	}
	public void setResponseDate(String responseDate) {
		this.responseDate = responseDate;
	}
	public Map<Object, Object> getData() {
		return data;
	}
	public void setData(Map<Object, Object> data) {
		this.data = data;
	}
	public String gettXid()
	{
		return tXid;
	}
	public void settXid( String tXid )
	{
		this.tXid = tXid;
	}	
}
