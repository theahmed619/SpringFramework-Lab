package com.example.yeshendrayt.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.example.yeshendrayt.entity.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(DuplicateProductException.class)
	public ResponseEntity<ErrorResponse> duplicateProductExceptionHandler(
			DuplicateProductException dpex, WebRequest webRequest) {
		
		ErrorResponse errorResponse=new ErrorResponse(dpex.getMessage(),webRequest.getDescription(false),"Duplicate Product Found");
	
		return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
	}

}
