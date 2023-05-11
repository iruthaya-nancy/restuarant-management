package com.example.restaurant.exception;

public class InternalServerException extends Exception{
	public InternalServerException(String errorMessage) 
	{
		super(errorMessage);
	}

}
