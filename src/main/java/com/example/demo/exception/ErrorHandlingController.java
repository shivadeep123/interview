package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandlingController {
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ExceptionResponse>generalException(Exception ex){
		
		ExceptionResponse er =new ExceptionResponse();
		er.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		er.setDescription(ex.getMessage());
		System.out.println(ex);
		return new ResponseEntity<ExceptionResponse>(er,HttpStatus.INTERNAL_SERVER_ERROR);
	}

	
	@ExceptionHandler(CustomException.class)
	public ResponseEntity<ExceptionResponse>customException(CustomException e){
		
		ExceptionResponse er =new ExceptionResponse();
		er.setCode(HttpStatus.BAD_REQUEST.value());
		er.setDescription(e.getMessage());
		System.out.println(e);
		return new ResponseEntity<ExceptionResponse>(er,HttpStatus.BAD_REQUEST);
	}
	
	public void throwGeneralException() throws Exception{
		Exception e=new Exception("General Exception Occured");
		throw e;
		
	}
	
	public void throwActiveUserException() throws CustomException{
		CustomException e =new CustomException();
		e.setCode(10);
		e.setMessage("You cannot delete active user.");
		throw e;
		
	}
	
	public void throwNoUserException() throws CustomException{
		CustomException e =new CustomException();
		e.setCode(20);
		e.setMessage("User details are not found.");
		throw e;
		
	}
	public void throwEmailRegistered() throws CustomException{
		CustomException e =new CustomException();
		e.setCode(30);
		e.setMessage("Email Already Registered");
		throw e;
		
	}
	public void throwDateofBirth() throws CustomException{
		CustomException e =new CustomException();
		e.setCode(40);
		e.setMessage("Date of Birth should not be more than currentdate");
		throw e;
		
	}
}
