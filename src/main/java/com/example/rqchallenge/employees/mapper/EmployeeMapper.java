package com.example.rqchallenge.employees.mapper;

import com.example.rqchallenge.employees.dto.DummyEmployeeResponse;
import com.example.rqchallenge.employees.dto.Employee;

public class EmployeeMapper {

    public static Employee mapToEmployee(DummyEmployeeResponse dummyEmployeeResponse, Employee employee){
        employee.setEmployee_age(Integer.parseInt(dummyEmployeeResponse.getAge()));
        employee.setEmployee_name(dummyEmployeeResponse.getName());
        employee.setEmployee_salary(Long.valueOf(dummyEmployeeResponse.getSalary()));
        employee.setId(dummyEmployeeResponse.getId());
        return employee;
    }
}
