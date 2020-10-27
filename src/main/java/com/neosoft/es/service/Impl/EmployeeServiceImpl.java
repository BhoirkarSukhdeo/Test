package com.neosoft.es.service.Impl;

import java.util.Date;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neosoft.es.dao.EmployeeDao;
import com.neosoft.es.dto.EmployeeDto;
import com.neosoft.es.entities.Employee;
import com.neosoft.es.service.EmployeeService;
import com.neosoft.es.utils.Utility;

/**
 * Employee Service Implementation
 * 
 * @author Sukhdeo Bhoirkar
 * @since 1.0
 */

@Service
public class EmployeeServiceImpl implements EmployeeService{
    
    Logger log = LoggerFactory.getLogger(EmployeeServiceImpl.class);
    
    @Autowired
    private EmployeeDao employeeDao;

    @Override
    public EmployeeDto createEmployee(EmployeeDto dto) throws Exception {
        Employee employee = null;
        try {
            if (null != employeeDao.getEmployeeByUserName(dto.getUsername()))
                throw new Exception("Employee already exists.");
            else {
                employee = Utility.getEmployeeEntityFromDto(dto);
                employee.setCreatedTs(new Date());
                
                employee =employeeDao.createEmployee(employee);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new Exception(e.getMessage());
        }
        return Utility.getEmployeeDtoFromEntity(employee);
    }

    @Override
    public EmployeeDto getEmployee(Integer id) throws Exception {
        Employee employee = null;
        try {
            employee = employeeDao.getEmployeeById(id).get();
            if (employee == null)
                throw new Exception("Employee not found");
        } catch (Exception e) {
            log.error(e.getMessage() + id);
            throw new Exception("Employee not found");
        }
        return Utility.getEmployeeDtoFromEntity(employee);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<EmployeeDto> employeeResponse = null;
        List<Employee> employees = null;
        try {
            employees = employeeDao.getAllEmployees();
            employeeResponse = employees.stream().map(x -> Utility.getEmployeeDtoFromEntity(x)).collect(Collectors.toList());
        } catch (Exception e) {
            log.error("Error while getting all Employees details");
        }
        return employeeResponse;
    }

    @Override
    public boolean removeEmployee(Integer id) {
        boolean result = false;
        try {
            result = employeeDao.deleteEmployee(id);
        } catch (Exception e) {
            log.error("Error while removing employee: " + id);
        }
        return result;
    }

    @Override
    public EmployeeDto updateEmployee(Integer id, EmployeeDto dto) throws Exception {
        Employee employee = null;
        try {
            employee = employeeDao.getEmployeeById(id).get();
            if (employee != null) {
                if (dto.getPassword() != null)
                    employee.setPassword(dto.getPassword());
                if (dto.getUsername()!= null)
                    employee.setUsername(dto.getUsername());
                if (dto.getCreatedTs() != null)
                    employee.setCreatedTs(new Date());
                if (dto.getName() != null)
                    employee.setName(dto.getName());
                if (dto.getGender() != null)
                    employee.setGender(dto.getGender());
                if(dto.getDeptId()>0)
                    employee.setDeptId(dto.getDeptId());
                employee = employeeDao.updateEmployeeDetails(employee);
            } else {
                log.error("Error updating details for employee: " + id);
                throw new Exception("employee not found in database");
            }
        } catch (Exception e) {
            log.error("Error updatting details for employee:" + dto.getId());
            throw new Exception("employee not found");
        }
        return Utility.getEmployeeDtoFromEntity(employee);
        
    }
    
    

}
