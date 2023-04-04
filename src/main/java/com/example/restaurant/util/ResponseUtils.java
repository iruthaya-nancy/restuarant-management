package com.example.restaurant.util;

import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseUtils {
	public ResponseUtils() {
	}

	public static ResponseEntity<HttpStatusResponse> 
	prepareSuccessResponse(String responseMessage, Object data) {
		if (Objects.isNull(responseMessage)) {
			responseMessage = "Request success.";
		}
		return new ResponseEntity<>
		(HttpStatusResponse.setHttpResponse(HttpStatus.OK.value(), responseMessage, data),
				HttpStatus.OK);

	}
}