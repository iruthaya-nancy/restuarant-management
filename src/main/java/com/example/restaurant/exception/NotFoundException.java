package com.example.restaurant.exception;

public class NotFoundException extends Exception 
{
	public NotFoundException(String errorMessage) 
	{
		super(errorMessage);
	}

}
