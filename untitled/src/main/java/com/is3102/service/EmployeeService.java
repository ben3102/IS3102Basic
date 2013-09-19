package com.is3102.service;

import com.is3102.entity.Employee;

import javax.ejb.Stateless;
import java.io.Serializable;

@Stateless
public class EmployeeService extends DataAccessService<Employee> implements Serializable{

    public EmployeeService(){
        super(Employee.class);
    }
}
