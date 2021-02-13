package com.example.EmployeeRstAPI.contrlleradvice;

import java.net.http.HttpHeaders;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.EmployeeRstAPI.errorresponse.ApiError;

import lombok.extern.slf4j.Slf4j;

@Order(value=Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
@Slf4j
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
	
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers,HttpStatus status,WebRequest request){
		System.err.println("hello from tavant");
		ApiError apiError=new ApiError(HttpStatus.BAD_REQUEST);
		
		apiError.addValidationErrors(ex.getFieldErrors());
		return buildResponseEntity(apiError);
		
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	protected ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException exception){
		System.out.println("inside the exception handler");
		ApiError apiError=new ApiError(HttpStatus.BAD_REQUEST);
		apiError.setMessage("validationError");
		apiError.addValidationErrors(exception.getConstraintViolations());
		return buildResponseEntity(apiError);
		
	}
	
	private ResponseEntity<Object> buildResponseEntity(ApiError apiError){
		
		return new ResponseEntity<Object>(apiError,apiError.getStatus());
	}
	

}
