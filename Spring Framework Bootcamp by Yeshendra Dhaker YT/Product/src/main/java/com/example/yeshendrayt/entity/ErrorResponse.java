package com.example.yeshendrayt.entity;

import java.time.LocalDateTime;

public class ErrorResponse {
	
	LocalDateTime timeStamp;
	String errorMessage;
	String errorDetails;
	String errorCode;
	public ErrorResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ErrorResponse(String errorMessage, String errorDetails, String errorCode) {
		this.timeStamp=LocalDateTime.now();
		this.errorMessage = errorMessage;
		this.errorDetails = errorDetails;
		this.errorCode = errorCode;
	}
	public LocalDateTime getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(LocalDateTime timeStamp) {
		this.timeStamp = timeStamp;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String getErrorDetails() {
		return errorDetails;
	}
	public void setErrorDetails(String errorDetails) {
		this.errorDetails = errorDetails;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	
	

}
