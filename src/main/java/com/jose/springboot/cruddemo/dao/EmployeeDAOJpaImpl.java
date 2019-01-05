package com.jose.springboot.cruddemo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import org.hibernate.Session;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jose.springboot.cruddemo.entity.Employee;

@Repository
public class EmployeeDAOJpaImpl implements EmployeeDAO {

	private EntityManager entityManager;

	// set up constructor injection
	@Autowired
	public EmployeeDAOJpaImpl(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}

	@Override
	public List<Employee> findAll() {

		// create a query
		Query theQuery = entityManager.createQuery("from Employee");  // using standard JPA API

		// execute query and get result list
		List<Employee> employees = theQuery.getResultList();  

		// return the results
		return employees;
	}

	@Override
	public Employee findById(int theId) {
	
		// get the employee
		Employee theEmployee = entityManager.find(Employee.class, theId);

		// return the employee
		return theEmployee;

	}

	@Override
	public void save(Employee theEmployee) {

		// save or update the employee
		Employee dbEmployee = entityManager.merge(theEmployee);
		// update with id from db ... so we can get generated id for save/insert
		theEmployee.setId(dbEmployee.getId());

	}

	@Override
	public void deleteById(int theId) {

  		// delete object with primary key
		Query theQuery = entityManager.createQuery("delete from Employee wher id=:employeeId");
		theQuery.setParameter("employeeId", theId);
        theQuery.executeUpdate();
	}

}
