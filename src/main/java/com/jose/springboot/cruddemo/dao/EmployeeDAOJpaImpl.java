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
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);

		// get the employee
		Employee theEmployee = currentSession.get(Employee.class, theId);

		// return the employee
		return theEmployee;

	}

	@Override
	public void save(Employee theEmployee) {
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);

		// save employee
		currentSession.saveOrUpdate(theEmployee);

	}

	@Override
	public void deleteById(int theId) {
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);

  		// delete object with primary key
		Query theQuery = currentSession.createQuery("delete from Employee wher id=:employeeId");
		theQuery.setParameter("employeeId", theId);
        theQuery.executeUpdate();
	}

}
