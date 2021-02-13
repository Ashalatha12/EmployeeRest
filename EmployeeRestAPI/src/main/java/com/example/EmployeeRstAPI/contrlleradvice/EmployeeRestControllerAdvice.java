package com.example.EmployeeRstAPI.contrlleradvice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.example.EmployeeRstAPI.errorresponse.ErrorResponse;
import com.example.EmployeeRstAPI.exception.EmployeeNotFoundException;

@ControllerAdvice
public class EmployeeRestControllerAdvice {
	
	@ExceptionHandler(EmployeeNotFoundException.class)
	
	public final ResponseEntity<ErrorResponse> handleEmployeeNotFoundException(EmployeeNotFoundException e,WebRequest request){
		List<String> details=new ArrayList<String>();
		
		details.add(e.getLocalizedMessage());
		
		ErrorResponse errorResponse=new ErrorResponse("INCORRECT_REQUEST",details);
		
		return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);
	}

}
