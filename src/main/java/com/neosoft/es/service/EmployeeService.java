package com.neosoft.es.service;

import java.util.List;

import com.neosoft.es.dto.EmployeeDto;

/**
 * Service class for employee management operations
 * 
 * @author Sukhdeo bhoirkar
 * @since 1.0
 */

public interface EmployeeService {

    // create new Employee with given details
    EmployeeDto createEmployee(EmployeeDto dto) throws Exception;

    // get employee details by user id
    EmployeeDto getEmployee(Integer id) throws Exception;

    // get all employees details
    List<EmployeeDto> getAllEmployees();

    // delete employee
    boolean removeEmployee(Integer id);

    // update employee details
    EmployeeDto updateEmployee(Integer id, EmployeeDto dto) throws Exception;

}
