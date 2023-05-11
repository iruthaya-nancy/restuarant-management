package com.example.restaurant.Controller;

import java.net.http.HttpHeaders;
import java.util.Set;

import org.json.JSONObject;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.restaurant.exception.InternalServerException;
import com.example.restaurant.util.HttpStatusResponse;
import com.example.restaurant.util.ResponseUtils;
import com.example.restaurant.exception.DuplicateException;
import com.example.restaurant.exception.ConstraintViolationException;

@ControllerAdvice
public class ControllerAdvicer extends ResponseEntityExceptionHandler {
	
	
	@ExceptionHandler(DuplicateException.class)
	public ResponseEntity<String> duplicateException(DuplicateException e) {

		 return ResponseEntity.status(HttpStatus.CONFLICT.value()).body(e.getMessage());

	}
	

		
	@ExceptionHandler(InternalServerException.class)
	public ResponseEntity<HttpStatusResponse> InternalServiceException(InternalServerException ex) {
		return ResponseUtils.prepareInternalServerErrorResponse("Internal server error");
	}
	
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<HttpStatusResponse> constraintViolation(ConstraintViolationException ex,HttpHeaders headers, HttpStatus status, WebRequest request){
		 return ResponseUtils.prepareConflictResponse(null)
;	}



	/**
	 * Method to find and display missing parameter in Rest API.
	 */
	protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("Parameter Name", ex.getParameterName());
		jsonObject.put("Parameter Type", ex.getParameterType());
		return new ResponseEntity<>(HttpStatusResponse.setHttpErrorResponse(status.value(),
				"Required parameter is not present", jsonObject), status);
	}

	/**
	 * Method to find and display HTTP method and Request URL which is not
	 * available.
	 */

	protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("HTTP method", ex.getHttpMethod());
		jsonObject.put("Request URL", ex.getRequestURL());
		return new ResponseEntity<>(
				HttpStatusResponse.setHttpErrorResponse(status.value(), "No HTTP handler found", jsonObject), status);
	}

	/**
	 * Method to find and display unsupported/supported HTTP method.
	 */

	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
		HttpHeaders headers, HttpStatus status, WebRequest request) {
		JSONObject jsonObject = new JSONObject();
		Set<HttpMethod> supportedMethods = ex.getSupportedHttpMethods();
		jsonObject.put("Unsupported HTTP method", ex.getMethod());
		jsonObject.put("Supported HTTP method", supportedMethods);
		return new ResponseEntity<>(
				HttpStatusResponse.setHttpErrorResponse(status.value(), "Invalid HTTP method", jsonObject), status);
	}

	/**
	 * Method to validate entity class required data.
	 */

	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
		HttpHeaders headers, HttpStatus status, WebRequest request) {
		JSONObject jsonObject = new JSONObject();
		for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
			jsonObject.put(fieldError.getField(), fieldError.getDefaultMessage());
		}
		return new ResponseEntity<>(
				HttpStatusResponse.setHttpErrorResponse(status.value(), "Validation failed", jsonObject), status);
	}

	/**
	 * Method to find and display unsupported/supported content type.
	 */

	protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("Unsupported content type", ex.getContentType());
		jsonObject.put("Supported content types", MediaType.toString(ex.getSupportedMediaTypes()));
		return new ResponseEntity<>(
				HttpStatusResponse.setHttpErrorResponse(status.value(), "Invalid content type", jsonObject), status);
	}

	/**
	 * Method to find required request body is missing.
	 */

	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
		HttpHeaders headers, HttpStatus status, WebRequest request) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("message", "Wrong body or no body in request");
		return new ResponseEntity<>(
				HttpStatusResponse.setHttpErrorResponse(status.value(), "Required request body is missing", jsonObject),
				status);
	}
}
