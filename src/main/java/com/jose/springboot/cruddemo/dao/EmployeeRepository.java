package com.jose.springboot.cruddemo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jose.springboot.cruddemo.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
         // entity type and primary key, no need to write any implementation class !!
}
