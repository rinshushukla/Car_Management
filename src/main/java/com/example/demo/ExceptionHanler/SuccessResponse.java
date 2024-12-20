package com.example.demo.ExceptionHanler;

import java.time.LocalDateTime;

import com.example.demo.Entity.Car;

public class SuccessResponse {
	private String message;
	private LocalDateTime timestamp;
	private Car car;

	public SuccessResponse(String message, LocalDateTime timestamp, Car car) {
        this.message = message;
        this.timestamp = timestamp;
        this.car = car;
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

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}
	
	
}