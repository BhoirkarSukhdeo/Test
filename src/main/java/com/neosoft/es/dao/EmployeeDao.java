package com.neosoft.es.dao;

import java.util.List;
import java.util.Optional;

import com.neosoft.es.entities.Employee;

/**
 * Interface for Employee dao
 * 
 * @author Sukhdeo Bhoirkar
 * @since 1.0
 */
public interface EmployeeDao {

    Employee getEmployeeByUserName(String username);

    Employee createEmployee(Employee employee);

    Optional<Employee> getEmployeeById(Integer id);

    List<Employee> getAllEmployees();

    boolean deleteEmployee(Integer id);

    Employee updateEmployeeDetails(Employee employee);

}
