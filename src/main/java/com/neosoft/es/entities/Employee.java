package com.neosoft.es.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * entity for Employee class
 * 
 * @author Sukhdeo Bhoirkar
 * @since 1.0
 *
 */


@Entity
@Table(name = "employees")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employee {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Column(name = "id", columnDefinition = "INTEGER", insertable = false, updatable = false, nullable = false)
    private int id;
    
    @Column(name = "name")
    private String name;
    
    @Column(name = "gender")
    private String gender;
    
    @Column(name = "username", length = 60, unique = true)
    private String username;
    
    @Column(name = "password")
    private String password;
    
    @Column(name = "createdts")
    private Date createdTs;
    
    @Column(name="dept_id")
    private int deptId;

}
