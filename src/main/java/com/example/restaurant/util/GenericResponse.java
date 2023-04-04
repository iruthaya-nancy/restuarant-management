package com.example.restaurant.util;

import java.util.Locale;

public class GenericResponse {
	
	    private String message;
	    private String error;
	    
	 
	    public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

		public String getError() {
			return error;
		}

		public void setError(String error) {
			this.error = error;
		}
		public GenericResponse(String string, Locale locale) {}
		public GenericResponse(String message) {
	        super();
	        this.message = message;
	    }
	 
	    public GenericResponse(String message, String error) {
	        super();
	        this.message = message;
	        this.error = error;
	    }

}
