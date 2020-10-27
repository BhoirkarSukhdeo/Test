package com.neosoft.es.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Dto for EmployeeDto class
 * 
 * @author Sukhdeo Bhoirkar
 * @since 1.0
 *
 */


@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeDto {
    
    private int id;
    private String name;
    private String gender;
    private String username;
    @JsonIgnore
    private String password;
    private Date createdTs;
    private int deptId;
    

}
