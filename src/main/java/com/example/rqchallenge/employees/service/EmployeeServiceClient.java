package com.example.rqchallenge.employees.service;

import com.example.rqchallenge.employees.dto.AllEmployeesResponse;
import com.example.rqchallenge.employees.dto.Employee;
import com.example.rqchallenge.employees.dto.EmployeeResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Optional;

@FeignClient(url = "${service.employee.url}", name = "EmployeeService")
public interface EmployeeServiceClient {

    @GetMapping(value = "/employees" , produces = "application/json")
    public AllEmployeesResponse getAllEmployees();


    @GetMapping(value = "/employee/{id}" , produces = "application/json")
    public Optional<EmployeeResponse> getEmployeeById(@PathVariable String id);



}
