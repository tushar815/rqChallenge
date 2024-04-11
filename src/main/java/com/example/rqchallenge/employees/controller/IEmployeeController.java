package com.example.rqchallenge.employees.controller;

import com.example.rqchallenge.employees.dto.Employee;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;


public interface IEmployeeController {

    @GetMapping()
    ResponseEntity<List<Employee>> getAllEmployees() throws IOException;

    @GetMapping("/search/{searchString}")
    ResponseEntity<List<Employee>> getEmployeesByNameSearch(@PathVariable String searchString) throws JsonProcessingException;

    @GetMapping("/{id}")
    ResponseEntity<Employee> getEmployeeById(@PathVariable String id) throws JsonProcessingException;

    @GetMapping("/highestSalary")
    ResponseEntity<Integer> getHighestSalaryOfEmployees() throws JsonProcessingException;

    @GetMapping("/topTenHighestEarningEmployeeNames")
    ResponseEntity<List<String>> getTopTenHighestEarningEmployeeNames() throws JsonProcessingException;

    @PostMapping()
    ResponseEntity<Employee> createEmployee(@RequestBody Map<String, Object> employeeInput);

    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteEmployeeById(@PathVariable String id);

}
