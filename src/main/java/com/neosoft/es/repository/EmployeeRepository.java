package com.neosoft.es.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.neosoft.es.entities.Employee;


/**
 * Employee repository
 * 
 * @author Sukhdeo Bhoirkar
 * @since 1.0
 */

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
    
    public Employee getByUsername(String username);

}
