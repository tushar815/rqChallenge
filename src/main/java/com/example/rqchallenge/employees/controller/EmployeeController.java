package com.example.rqchallenge.employees.controller;

import com.example.rqchallenge.employees.dto.Employee;
import com.example.rqchallenge.employees.service.EmployeeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("employee")
@AllArgsConstructor
@Slf4j
public class EmployeeController implements IEmployeeController {


    private EmployeeService employeeService;

    @Override
    public ResponseEntity<List<Employee>> getAllEmployees() throws IOException {
        log.debug("getAllEmployee() method called");
        List<Employee> employeeList = employeeService.getAllEmployees();
        log.debug("getAllEmployee() returned {] Employees", employeeList.size());
        return ResponseEntity
                .status(HttpStatus.OK).
                body(employeeList);
    }

    @Override
    public ResponseEntity<List<Employee>> getEmployeesByNameSearch(String searchString) throws JsonProcessingException {
        log.debug("getEmployeesByNameSearch() method called");
        List<Employee> employeesList = employeeService.getEmployeesByNameSearch(searchString);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(employeesList);
    }



    @Override
    public ResponseEntity<Employee> getEmployeeById(String id) throws JsonProcessingException {
        log.debug("getEmployeeById() method called");
        Employee employee = employeeService.getEmployeeById(id);
        log.debug("getEmployeeById() returned with {} Employee", employee);
        return ResponseEntity
                .status(HttpStatus.OK).
                body(employee);
    }

    @Override
    public ResponseEntity<Integer> getHighestSalaryOfEmployees() throws JsonProcessingException {
        log.debug("getHighestSalaryOfEmployees() method called");
         Integer salary = employeeService.getHighestSalaryOfEmployees();
        log.debug("getHighestSalaryOfEmployees() returned with {} salary", salary);
        return ResponseEntity
                .status(HttpStatus.OK).
                body(salary);
    }

    @Override
    public ResponseEntity<List<String>> getTopTenHighestEarningEmployeeNames() throws JsonProcessingException {

        log.debug("getHighestSalaryOfEmployees() method called");
       List<String> salaries = employeeService.getTop10HighestEarningEmployeeNames();
        log.debug("getHighestSalaryOfEmployees() returned with {} salary", salaries);
        return ResponseEntity
                .status(HttpStatus.OK).
                body(salaries);

    }

    @Override
    public ResponseEntity<Employee> createEmployee(Map<String, Object> employeeInput) {
        return null;
    }

    @Override
    public ResponseEntity<String> deleteEmployeeById(String id) {
        return null;
    }
}
