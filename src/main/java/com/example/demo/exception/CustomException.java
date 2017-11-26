package com.example.demo.exception;

import org.springframework.stereotype.Component;

@Component
public class CustomException extends Exception{
	
	
	public static final long serialversionUID = 1L;
	
	private int code;
	private String message;
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	

}
