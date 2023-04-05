package com.example.restaurant.exception;

public class BusinessServiceException extends Exception{
	
	public BusinessServiceException(String errorMessage) 
	{
		super(errorMessage);
	}

}
