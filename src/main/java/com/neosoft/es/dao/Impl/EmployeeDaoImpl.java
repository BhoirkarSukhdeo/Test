package com.neosoft.es.dao.Impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.neosoft.es.dao.EmployeeDao;
import com.neosoft.es.entities.Employee;
import com.neosoft.es.repository.EmployeeRepository;

/**
 * Employee dao interface implementation
 * 
 * @author Sukhdeo Bhoirkar
 * @since 1.0
 */
@Repository
public class EmployeeDaoImpl implements EmployeeDao {

    Logger log = LoggerFactory.getLogger(EmployeeDaoImpl.class);

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public Employee getEmployeeByUserName(String username) {
        return employeeRepository.getByUsername(username);
    }

    @Override
    public Employee createEmployee(Employee employee) {
        Employee created = null;
        try {
            created = employeeRepository.save(employee);
        } catch (Exception e) {
            log.info("DB save error" + e.getMessage());
        }
        return created;
    }

    @Override
    public java.util.Optional<Employee> getEmployeeById(Integer id) {
        return employeeRepository.findById(id);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public boolean deleteEmployee(Integer id) {
        try {
            employeeRepository.deleteById(id);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public Employee updateEmployeeDetails(Employee employee) {
        Employee updated = null;
        try {
            updated = employeeRepository.save(employee);
        } catch (Exception e) {
        }
        return updated;
    }

}
