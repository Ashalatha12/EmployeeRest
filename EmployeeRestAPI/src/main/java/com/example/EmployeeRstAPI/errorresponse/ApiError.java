package com.example.EmployeeRstAPI.errorresponse;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;

import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import com.example.EmployeeRstAPI.utils.LowerCaseClassNameResolver;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonTypeIdResolver;

import lombok.Data;


@Data
@JsonTypeInfo(include= JsonTypeInfo.As.WRAPPER_OBJECT,use=JsonTypeInfo.Id.CUSTOM,property="error",visible=true)
@JsonTypeIdResolver(LowerCaseClassNameResolver.class)
public class ApiError implements Serializable
{
	
	private static final long serialVersionUID=1L;
	private HttpStatus status;
	@JsonFormat(shape=JsonFormat.Shape.STRING,pattern="dd-MM-yyyy hh:mm:ss" )
	private LocalDateTime timeStamp;
	private String message;
	private String debugMessage;
	private List<ApiSubError> subErros;
	 private ApiError() {
		 timeStamp=LocalDateTime.now();
	 }
	public ApiError(HttpStatus status) {
		this();
		this.status=status;
	}
	public ApiError(HttpStatus status,Throwable ex) {
		this();
		this.status=status;
		this.message="Unexpected error";
		this.debugMessage=ex.getLocalizedMessage();
	}
	
	public ApiError(HttpStatus status,String message,Throwable ex) {
		this();
		this.status=status;
		this.message=message;
		this.debugMessage=ex.getLocalizedMessage();
		}
	
	private void adddSubError(ApiSubError subError) {
		if(subErros==null) {
			subErros=new ArrayList<>();
		}
		subErros.add(subError);
	}
	private void addValidationError(String object,String field,Object rejectedValue,String message) {
		adddSubError(new ApiValidationError(object, field, rejectedValue,message));
	}
	
	private void AddValidationError(String object,String message) {
		adddSubError(new ApiValidationError(object, message));
	}
	
	public void AddValidationerror(FieldError fieldError) {
		this.addValidationError(
				fieldError.getObjectName(),
				fieldError.getField(),
				fieldError.getRejectedValue(),
				fieldError.getDefaultMessage()
				);
	}
	public void addValidationErrors(List<FieldError> fieldErrors) {
		fieldErrors.forEach(this::addValidationError);
	}
	
	private void addValidationError(ObjectError objectError) {
		this.AddValidationError(
				objectError.getObjectName(),
				objectError.getDefaultMessage()
				);
	}
	public void addvalidatorError(List<ObjectError> globalErrors) {
		globalErrors.forEach(this::addValidationError);
	}
	private void addvalidationerror(ConstraintViolation<?> cv) {
		this.addValidationError(
				cv.getRootBeanClass().getSimpleName(),
				((PathImpl) cv.getPropertyPath()).getLeafNode().asString(),
				cv.getInvalidValue(),
				cv.getMessage());	
		
		
	}
	public void addValidationErrors(Set<ConstraintViolation<?>> constraintViolations) {
		constraintViolations.forEach(this::addvalidationerror);
	}

}
