package com.example.rqchallenge.employees;

import com.example.rqchallenge.employees.dto.Employee;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.List;
import java.util.Map;


public interface IEmployeeController {

    @GetMapping()
    @Operation(summary = "get All Employees")
    @ApiResponse(
            responseCode = "200",
            description = "HTTP status Ok"
    )
    ResponseEntity<List<Employee>> getAllEmployees() throws IOException;

    @GetMapping("/search/{searchString}")
    @Operation(summary = "get employee by name")
    @ApiResponse(
            responseCode = "200",
            description = "HTTP status Ok"
    )
    ResponseEntity<List<Employee>> getEmployeesByNameSearch(@PathVariable  @NotNull @NotBlank String searchString) throws JsonProcessingException;

    @GetMapping("/{id}")
    @Operation(summary = "get employee by id")
    @ApiResponse(
            responseCode = "200",
            description = "HTTP status Ok"
    )
    ResponseEntity<Employee> getEmployeeById(@PathVariable  @NotNull @NotBlank String id) throws JsonProcessingException;

    @GetMapping("/highestSalary")
    @Operation(summary = "get highest salary among all employees")
    @ApiResponse(
            responseCode = "200",
            description = "HTTP status Ok"
    )
    ResponseEntity<Integer> getHighestSalaryOfEmployees() throws JsonProcessingException;

    @GetMapping("/topTenHighestEarningEmployeeNames")
    @Operation(summary = "get top ten highest salaries among all employees")
    @ApiResponse(
            responseCode = "200",
            description = "HTTP status Ok"
    )
    ResponseEntity<List<String>> getTopTenHighestEarningEmployeeNames() throws JsonProcessingException;

    @PostMapping()
    @Operation(summary = "create a employee")
    @ApiResponse(
            responseCode = "201",
            description = "HTTP status created"
    )
    ResponseEntity<Employee> createEmployee(@Valid @NotNull @RequestBody Map<String, Object> employeeInput) throws Exception;

    @DeleteMapping("/{id}")
    @Operation(summary = "delete a employee with given id")
    @ApiResponse(
            responseCode = "204",
            description = "HTTP status no content"
    )
    ResponseEntity<String> deleteEmployeeById( @NotNull @NotBlank @PathVariable String id) throws Exception;

}
