package com.neosoft.es.rest;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neosoft.es.dto.EmployeeDto;
import com.neosoft.es.service.EmployeeService;
import com.neosoft.es.utils.Constants;

import io.swagger.annotations.ApiOperation;


/**
 * Controller for employee operations
 * 
 * @author Sukhdeo Bhoirkar
 * @since 1.0
 */

@RestController
@RequestMapping(Constants.BASE_URL)
public class EmployeeController {
    
    Logger log = LoggerFactory.getLogger(EmployeeController.class);
    
    @Autowired
    private EmployeeService employeeService;
    
    @ApiOperation("API for register new employee")
    @PostMapping(value = Constants.REGISTER_USER_URL, consumes = "application/json")
    public ResponseEntity<?> registerEmployee(@RequestBody EmployeeDto dto) {
        ResponseEntity<?> response = null;
        EmployeeDto createdEmployee = null;
        try {
            createdEmployee = employeeService.createEmployee(dto);
            response = new ResponseEntity<EmployeeDto>(createdEmployee, HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("Employee registration failed: " + dto.getUsername());
            response = new ResponseEntity<String>("{\"msg\": \"" + e.getMessage() + "\"}", HttpStatus.BAD_REQUEST);
        }
        return response;
    }
    
    
    @ApiOperation("API for Get Employee details as per Employee Id")
    @GetMapping(value = Constants.EMPLOYEE_URL)
    public ResponseEntity<?> getEmployeeDetails(@PathVariable("id") Integer id) {
        ResponseEntity<?> response = null;
        EmployeeDto dto = null;
        try {
            dto = employeeService.getEmployee(id);
            response = new ResponseEntity<EmployeeDto>(dto, HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
            response = new ResponseEntity<String>("{\"msg\": \"" + e.getMessage() + " \"}", HttpStatus.NOT_FOUND);
        }
        return response;
    }
    
    @SuppressWarnings("rawtypes")
    @ApiOperation("API for Get all Employee details")
    @GetMapping(value = Constants.GET_ALL_EMPLOYEES_URL)
    public ResponseEntity<?> getAllEmployees() {
        ResponseEntity<?> response = null;
        List<EmployeeDto> employees = null;
        try {
            employees = employeeService.getAllEmployees();
            response = new ResponseEntity<List>(employees, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Failed to get all employees details");
            response = new ResponseEntity<String>("{\"msg\": \"" + e.getMessage() + "\"}",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }
    
    @ApiOperation("API for Remove the Employee")
    @DeleteMapping(value = Constants.EMPLOYEE_URL)
    public ResponseEntity<?> removeEmployee(@PathVariable("id") Integer id) {
        ResponseEntity<?> response = null;
        boolean changes = false;
        try {
            changes = employeeService.removeEmployee(id);
        } catch (Exception e) {
            log.error("Erorr deleting Employee " + id);
        }
        if (changes)
            response = new ResponseEntity<String>("{\"msg\": \"employee deleted successfully\"}", HttpStatus.OK);
        else
            response = new ResponseEntity<String>("{\"msg\": \"Erorr deleting employee\"} ", HttpStatus.BAD_REQUEST);
        return response;
    }

    @ApiOperation("API for update/change employee details")
    @PatchMapping(value = Constants.EMPLOYEE_URL, consumes = "application/json")
    public ResponseEntity<?> updateEmployee(@PathVariable("id") Integer id, @RequestBody EmployeeDto dto) {
        ResponseEntity<?> response = null;
        try {
             employeeService.updateEmployee(id, dto);
            response = new ResponseEntity<String>("{\"msg\": \"Employee details updated successfully\"}", HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error updating Employee details");
            response = new ResponseEntity<String>("{\"msg\": \"Error updating Employee details\"}", HttpStatus.BAD_REQUEST);
        }
        return response;
    }
}
