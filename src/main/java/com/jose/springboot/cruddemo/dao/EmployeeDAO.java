package com.jose.springboot.cruddemo.dao;

import java.util.List;

import com.jose.springboot.cruddemo.entity.Employee;

public interface EmployeeDAO {
	
	public List<Employee> findAll();

}
