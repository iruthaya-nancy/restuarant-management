package com.example.restaurant.util;

import java.io.Serializable;

import com.fasterxml.jackson.databind.util.JSONPObject;


public class HttpStatusResponse implements Serializable {
	
	private static HttpStatusResponse httpResponse;
	private Integer statusCode; 
	private String description; 
	private Object data;
	private String customeCode;
	private JSONPObject error;
	public Integer getStatusCode() {
		return statusCode;
	}



	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public Object getData() {
		return data;
	}



	public void setData(Object data) {
		this.data = data;
	}



	public String getCustomeCode() {
		return customeCode;
	}



	public void setCustomeCode(String customeCode) {
		this.customeCode = customeCode;
	}

	

	public JSONPObject getError() {
		return error;
	}



	public void setError(JSONPObject error) {
		this.error = error;
	}



	private HttpStatusResponse() 
	{
		super();
	}
	
	public static HttpStatusResponse getInstance() {
		if (httpResponse == null) 
		{
			httpResponse = new HttpStatusResponse(); 
		} return httpResponse;
	}
	
	public static Object setHttpResponse(int value, String responseMessage, Object data) {
		// TODO Auto-generated method stub
		return null;
	}

}
