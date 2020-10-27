package com.neosoft.es.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.neosoft.es.dto.EmployeeDto;
import com.neosoft.es.entities.Employee;
/**
 * Utility class for employee service
 * 
 * @author  Sukhdeo Bhoirkar
 * @since 1.0
 */
public class Utility {
    private static Logger log = LoggerFactory.getLogger(Utility.class);

    public static Employee getEmployeeEntityFromDto(EmployeeDto dto) {

        Employee employee = null;
        try {
            employee = new Employee();
            employee.setId(dto.getId());
            employee.setName(dto.getName());
            employee.setUsername(dto.getUsername());
            employee.setPassword(dto.getPassword());
            employee.setGender(dto.getGender());
            employee.setDeptId(dto.getDeptId());
            employee.setCreatedTs(dto.getCreatedTs());
        } catch (Exception e) {
            employee = null;
            log.error("Error while parsing employee entity");
        }
        return employee;
    }

    public static EmployeeDto getEmployeeDtoFromEntity(Employee employee) {

        EmployeeDto dto = null;
        try {
            dto = new EmployeeDto();
            dto.setId(employee.getId());
            dto.setName(employee.getName());
            dto.setUsername(employee.getUsername());
            dto.setPassword(employee.getPassword());
            dto.setGender(employee.getGender());
            dto.setDeptId(employee.getDeptId());
            dto.setCreatedTs(employee.getCreatedTs());
        } catch (Exception e) {
            employee = null;
            log.error("Error while parsing employee entity");
        }
        return dto;
    }

}
