package com.example.rqchallenge.employees.service;

import com.example.rqchallenge.employees.dto.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Optional;

@FeignClient(url = "${service.employee.url}", name = "EmployeeService")
public interface EmployeeServiceClient {

    @GetMapping(value = "/employees" , produces = "application/json")
    public AllEmployeesResponse getAllEmployees();


    @GetMapping(value = "/employee/{id}" , produces = "application/json")
    public EmployeeResponse getEmployeeById(@PathVariable String id);

    @PostMapping(value = "/create", produces = "application/json")
    public EmployeeCreateResponse createEmployee(@RequestBody EmployeeRequest request);

    @DeleteMapping(value = "/delete/{id}", produces = "application/json")
    public BaseResponse deleteEmployeeById(@PathVariable String id);

}
