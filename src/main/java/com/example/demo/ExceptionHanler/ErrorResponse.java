package com.example.demo.ExceptionHanler;

import java.time.LocalDateTime;

public class ErrorResponse {
    private String message;
    private LocalDateTime timestamp;
    private String errorDetails;
    private String errorCode;

    public ErrorResponse(String message, LocalDateTime timestamp, String errorDetails, String errorCode) {
        this.message = message;
        this.timestamp = timestamp;
        this.errorDetails = errorDetails;
        this.errorCode = errorCode;
    }

    // Getters and Setters
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
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
