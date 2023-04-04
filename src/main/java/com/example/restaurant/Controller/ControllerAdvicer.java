package com.example.restaurant.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.restaurant.exception.UserNotFoundException;

@ControllerAdvice
public class ControllerAdvicer extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(UserNotFoundException.class)
	
	 public ResponseEntity<String> handleMyException(UserNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
