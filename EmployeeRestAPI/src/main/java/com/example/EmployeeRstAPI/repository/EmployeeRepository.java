package com.example.EmployeeRstAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.EmployeeRstAPI.model.Employee;
//to use spring data jpa repository we have to extend the interface to jpa repository and add the class

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
