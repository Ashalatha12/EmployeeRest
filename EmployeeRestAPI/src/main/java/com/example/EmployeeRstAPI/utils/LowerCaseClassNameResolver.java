package com.example.EmployeeRstAPI.utils;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.fasterxml.jackson.databind.jsontype.impl.TypeIdResolverBase;

public class LowerCaseClassNameResolver  extends TypeIdResolverBase{
	
	public String idFormValue(Object value) {
		return value.getClass().getSimpleName().toLowerCase();
	}
	


	@Override
	public String idFromValue(Object value) {
		// TODO Auto-generated method stub
	 return idFromValue(value);
	}

	@Override
	public Id getMechanism() {
		// TODO Auto-generated method stub
		return JsonTypeInfo.Id.CUSTOM;
	}



	@Override
	public String idFromValueAndType(Object value, Class<?> suggestedType) {
		// TODO Auto-generated method stub
		return null;
	}

}
