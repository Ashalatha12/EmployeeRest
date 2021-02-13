package com.example.EmployeeRstAPI.errorresponse;

import java.io.Serializable;

import lombok.Data;

@Data
public class ApiValidationError  extends ApiSubError implements Serializable{
	
	private String object;
	private String field;
	private Object rejectedValue;
	private String message;
	
	public ApiValidationError(String object,String field,Object rejectedValue2, String message2) {
		
		this.object=object;
		this.field=field;
		this.rejectedValue=rejectedValue2;
		this.message=message2;
	}
	public ApiValidationError(String object2,String message2) {
		this.object=object2;
		this.message=message2;
	}

}
