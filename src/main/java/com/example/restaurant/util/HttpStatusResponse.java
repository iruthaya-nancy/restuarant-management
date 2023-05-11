package com.example.restaurant.util;

import java.io.Serializable;

import org.json.JSONObject;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


@JsonInclude(value = Include.NON_NULL)
public class HttpStatusResponse implements Serializable {
	
	private static HttpStatusResponse httpResponse;
	
	private Integer totalRecords;

	private Integer statusCode; 
	private String description; 
	private Object data;
	private String customeCode;
	private JSONObject error;
	
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

	public JSONObject getError() {
		return error;
	}



	public void setError(JSONObject error) {
		this.error = error;
	}
	


	public Integer getTotalRecords() {
		return totalRecords;
	}



	public void setTotalRecords(Integer totalRecords) {
		this.totalRecords = totalRecords;
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
	//
	public static HttpStatusResponse setHttpResponse(Integer totalRecords, Integer code,
			String description, Object data) {
		httpResponse = new HttpStatusResponse(); 
		httpResponse.setTotalRecords(totalRecords);
		httpResponse.setStatusCode(code);
		httpResponse.setDescription(description); 
		httpResponse.setData(data);
		return httpResponse;
	}
	//code,description,data,custom code
	public static HttpStatusResponse setHttpResponse(Integer code, String description,
			Object data, String customCode) 
	{
		httpResponse = new HttpStatusResponse(); 
		httpResponse.setTotalRecords(null); 
		httpResponse.setStatusCode(code); 
		httpResponse.setDescription(description); 
		httpResponse.setData(data); 
		httpResponse.setCustomeCode(customCode); 
		return httpResponse; 
	}
	
	public static HttpStatusResponse setHttpErrorResponse(Integer code, String description,
			JSONObject error) 
	{ 
		HttpStatusResponse httpResponse = new HttpStatusResponse();
		httpResponse.setTotalRecords(null); 
		httpResponse.setStatusCode(code); 
		httpResponse.setDescription(description); 
		httpResponse.setError(error);
		return httpResponse; }



	public static  HttpStatusResponse setHttpResponse(Integer code, String description, Object data) {
		HttpStatusResponse httpResponse = new HttpStatusResponse();
		httpResponse.setStatusCode(code);
		httpResponse.setDescription(description); 
		httpResponse.setData(data); 
		
		return httpResponse;
	}



	public static Object setHttpCostraintResponse(Integer code, String description) {
		// TODO Auto-generated method stub
		HttpStatusResponse httpResponse = new HttpStatusResponse();
		httpResponse.setStatusCode(code);
		httpResponse.setDescription(description);
		return httpResponse;
	}
	
	
    

}
