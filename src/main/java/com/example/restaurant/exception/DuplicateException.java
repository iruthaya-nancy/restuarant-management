package com.example.restaurant.exception;

public class DuplicateException extends RuntimeException {
	
	
	   public DuplicateException(String message) 
	   {
	        super(message);
	        
	    }
	   
	   public DuplicateException(Exception e,String message) 
	   {
	        super(message,e);
	        
	    }


}
