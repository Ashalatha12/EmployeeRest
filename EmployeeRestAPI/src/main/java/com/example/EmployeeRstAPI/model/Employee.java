package com.example.EmployeeRstAPI.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Component
@Entity(name = "Employee")
@Table(name = "employees")
public class Employee {

	
	@Id
	private Integer employeeId;
	
    @NotBlank(message="name shouldnot be blank")
	private String firstName;
    
	@NotBlank(message="last name shouldnot be blank")
	private String lastName;
	
	
	@Email(message = "email should be valid")
	private String email;
	
	private String jobTitle;
	
	
	private String officeCode;
	
	
	
}