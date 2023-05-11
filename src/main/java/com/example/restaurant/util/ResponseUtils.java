package com.example.restaurant.util;

import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseUtils {
	private ResponseUtils() {
	}

	public static ResponseEntity<HttpStatusResponse> prepareSuccessResponse(String responseMessage, Object data) {
		if (Objects.isNull(responseMessage)) {
			responseMessage = "Request success.";
		}
		return new ResponseEntity<>(HttpStatusResponse.setHttpResponse(HttpStatus.OK.value(), responseMessage, data),
				HttpStatus.OK);
	}

	public static ResponseEntity<HttpStatusResponse> prepareUnAuthorizedResponse(String responseMessage) {
		if (Objects.isNull(responseMessage)) {
			responseMessage = "Unauthorized Request";
		}
		return new ResponseEntity<>(
				HttpStatusResponse.setHttpResponse(HttpStatus.UNAUTHORIZED.value(), responseMessage, null),
				HttpStatus.UNAUTHORIZED);
	}

	public static ResponseEntity<HttpStatusResponse> prepareAcceptedResponse(String responseMessage, Object data) {
		if (Objects.isNull(responseMessage)) {
			responseMessage = "Request Accepted.";
		}
		return new ResponseEntity<>(
				HttpStatusResponse.setHttpResponse(HttpStatus.ACCEPTED.value(), responseMessage, data),
				HttpStatus.ACCEPTED);
	}

	public static ResponseEntity<HttpStatusResponse> prepareForbiddenResponse(String responseMessage) {
		if (Objects.isNull(responseMessage)) {
			responseMessage = "Forbidden Request.";
		}
		return new ResponseEntity<>(
				HttpStatusResponse.setHttpResponse(HttpStatus.FORBIDDEN.value(), responseMessage, null),
				HttpStatus.FORBIDDEN);
	}

	public static ResponseEntity<HttpStatusResponse> prepareUnProcessableEntityResponse(String responseMessage) {
		if (Objects.isNull(responseMessage)) {
			responseMessage = "Input(s) are invalid.";
		}
		return new ResponseEntity<>(
				HttpStatusResponse.setHttpResponse(HttpStatus.UNPROCESSABLE_ENTITY.value(), responseMessage, null),
				HttpStatus.UNPROCESSABLE_ENTITY);
	}

	public static ResponseEntity<HttpStatusResponse> prepareConflictResponse(String responseMessage) {
		if (Objects.isNull(responseMessage)) {
			responseMessage = "Some preconditions failed";
		}
		return new ResponseEntity<>(
				HttpStatusResponse.setHttpResponse(HttpStatus.CONFLICT.value(), responseMessage, null),
				HttpStatus.CONFLICT);
	}

	public static ResponseEntity<HttpStatusResponse> prepareInternalServerErrorResponse(String responseMessage) {
		if (Objects.isNull(responseMessage)) {
			responseMessage = "Something went wrong.";
		}
		return new ResponseEntity<>(
				HttpStatusResponse.setHttpResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), responseMessage, null),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

	public static ResponseEntity<HttpStatusResponse> prepareResponse(HttpStatus httpStatus, String responseMessage,
			String customCode) {
		if (Objects.isNull(responseMessage)) {
			responseMessage = "Something went wrong";
		}
		return new ResponseEntity<>(
				HttpStatusResponse.setHttpResponse(httpStatus.value(), responseMessage, null, customCode), httpStatus);
	}

	public static ResponseEntity<HttpStatusResponse> prepareUnProcessableEntityResponseWithData(String responseMessage,
			Object data, Integer statusCode) {
		if (Objects.isNull(responseMessage)) {
			responseMessage = "Input(s) are invalid.";
		}
		return new ResponseEntity<>(HttpStatusResponse.setHttpResponse(statusCode, responseMessage, data),
				HttpStatus.UNPROCESSABLE_ENTITY);
	}

	public static ResponseEntity<HttpStatusResponse> prepareSuccessResponse(String responseMessage, Object data,
			HttpStatus statusCode) {
		if (Objects.isNull(responseMessage)) {
			responseMessage = "Request success.";
		}
		return new ResponseEntity<>(HttpStatusResponse.setHttpResponse(statusCode.value(), responseMessage, data),
				statusCode);
	}

	public static ResponseEntity<HttpStatusResponse> prepareBadRequestResponse(String responseMessage) {
		if (Objects.isNull(responseMessage)) {
			responseMessage = "Bad Request";
		}
		return new ResponseEntity<>(
				HttpStatusResponse.setHttpResponse(HttpStatus.BAD_REQUEST.value(), responseMessage, null),
				HttpStatus.BAD_REQUEST);
	}

	public static ResponseEntity<HttpStatusResponse> prepareNoRecordFoundResponse(String responseMessage) {
		if (Objects.isNull(responseMessage)) {
			responseMessage = "No Content";
		}
		return new ResponseEntity<>(
				HttpStatusResponse.setHttpResponse(HttpStatus.NOT_FOUND.value(), responseMessage, null),
				HttpStatus.NOT_FOUND);
	}

}