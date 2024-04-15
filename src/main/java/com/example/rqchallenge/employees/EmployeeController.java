package com.example.rqchallenge.employees;

import com.example.rqchallenge.employees.dto.Employee;
import com.example.rqchallenge.employees.exception.BadRequestBodyException;
import com.example.rqchallenge.employees.service.EmployeeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("employee")
@AllArgsConstructor
@Validated
@Slf4j
public class EmployeeController implements IEmployeeController {


    private EmployeeService employeeService;

    @Override
    public ResponseEntity<List<Employee>> getAllEmployees() throws IOException {
        log.debug("Calling getAllEmployees from Controller getAllEmployees");
        List<Employee> employeeList = employeeService.getAllEmployees();
        log.debug("getAllEmployees() returned {} Employees", employeeList.size());
        return ResponseEntity
                .status(HttpStatus.OK).
                body(employeeList);
    }

    @Override
    public ResponseEntity<List<Employee>> getEmployeesByNameSearch(String searchString) throws JsonProcessingException {
        log.debug("Calling getEmployeesByNameSearch from Controller getEmployeesByNameSearch");
        List<Employee> employeesList = employeeService.getEmployeesByNameSearch(searchString);
        log.debug("getAllEmployees() returned {} Employees", employeesList.size());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(employeesList);
    }



    @Override
    public ResponseEntity<Employee> getEmployeeById(String id) throws JsonProcessingException {
        log.debug("Calling getEmployeeById from Controller getEmployeeById");
        Employee employee = employeeService.getEmployeeById(id);
        log.debug("getAllEmployees() returned employee with name {} ", employee.getEmployee_name());
        return ResponseEntity
                .status(HttpStatus.OK).
                body(employee);
    }

    @Override
    public ResponseEntity<Integer> getHighestSalaryOfEmployees() throws JsonProcessingException {
        log.debug("Calling getHighestSalaryOfEmployees from Controller getHighestSalaryOfEmployees");
         Integer salary = employeeService.getHighestSalaryOfEmployees();
        log.debug("getHighestSalaryOfEmployees() returned with {} salary", salary);
        return ResponseEntity
                .status(HttpStatus.OK).
                body(salary);
    }

    @Override
    public ResponseEntity<List<String>> getTopTenHighestEarningEmployeeNames() throws JsonProcessingException {

        log.debug("Calling getTopTenHighestEarningEmployeeNames from Controller getTopTenHighestEarningEmployeeNames");
       List<String> salaries = employeeService.getTop10HighestEarningEmployeeNames();
        log.debug("getHighestSalaryOfEmployees() returned with {} salary", salaries);
        return ResponseEntity
                .status(HttpStatus.OK).
                body(salaries);

    }

    @Override
    public ResponseEntity<Employee> createEmployee(Map<String, Object> employeeInput) throws Exception {
        if (!employeeInput.containsKey("name") || !employeeInput.containsKey("age") || !employeeInput.containsKey("department")) {
            throw new BadRequestBodyException("Missing required data");
        }

        log.debug("Calling createEmployee from Controller createEmployee");
        Employee employee = employeeService.createEmployee(employeeInput);
        log.debug("createEmployee() returned with {} employee", employee);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(employee);
    }

    @Override
    public ResponseEntity<String> deleteEmployeeById(String id) throws Exception {
        log.debug("Calling deleteEmployeeById from Controller deleteEmployeeById");
        String name = employeeService.deleteEmployeeById(id);
        log.debug("deleteEmployeeById() deleted employee  with name {} ", name);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body(name);
    }
}
