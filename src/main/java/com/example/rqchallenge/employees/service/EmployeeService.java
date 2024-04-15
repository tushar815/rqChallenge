package com.example.rqchallenge.employees.service;


import com.example.rqchallenge.employees.dto.*;
import com.example.rqchallenge.employees.exception.EmployeeDoesNotExistException;
import com.example.rqchallenge.employees.mapper.EmployeeMapper;
import com.example.rqchallenge.employees.util.MockData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class EmployeeService {

    private EmployeeServiceClient employeeServiceClient;

    @Value("${service.useMockData}")
    private boolean useMockData;

    public EmployeeService(EmployeeServiceClient employeeServiceClient) {
        this.employeeServiceClient = employeeServiceClient;
    }

    public List<Employee> getAllEmployees() throws JsonProcessingException {
        log.debug("Calling Employee Service Client to Get All Employees");
        AllEmployeesResponse employeesResponse =  useMockData ? MockData.getAllEmployeeMock() :employeeServiceClient.getAllEmployees();
        return new ArrayList<>(employeesResponse.getData());
    }

    public Employee getEmployeeById(String id) throws JsonProcessingException {
        log.debug("Calling Employee Service Client to Get employee for given id {}", id);
        EmployeeResponse employeeResponse = useMockData ? MockData.getEmployeeById(id) : employeeServiceClient.getEmployeeById(id);
        if(Optional.ofNullable(employeeResponse.getData()).isPresent()){
            return employeeResponse.getData();

        }else{
            log.error("Employee with given id {} does not exist!", id);
            throw new EmployeeDoesNotExistException("Employee with given id "+id +" does not exist!");

        }
    }

    public List<Employee> getEmployeesByNameSearch(String employeeName) throws JsonProcessingException {
        log.debug("Calling Employee Service Client to Get all employees matching given name {}", employeeName);

        AllEmployeesResponse employeesResponse =  useMockData ? MockData.getAllEmployeeMock() : employeeServiceClient.getAllEmployees();
        List<Employee> employeeList = employeesResponse.getData();

        if(Optional.ofNullable(employeeList).isPresent()){
            return employeeList
                    .stream()
                    .filter(employee -> employee.getEmployee_name().toLowerCase().contains(employeeName.toLowerCase()))
                    .collect(Collectors.toList());
        }else {
            log.error("Employee with given name {}  does not exist!", employeeName);
            throw new EmployeeDoesNotExistException("Employee with given name "+ employeeName +" does not exist!");
        }

    }

    public Integer getHighestSalaryOfEmployees() throws JsonProcessingException {
        log.debug("Calling Employee Service Client to Get Highest Salary of employess");

        AllEmployeesResponse employeesResponse =  useMockData ? MockData.getAllEmployeeMock() : employeeServiceClient.getAllEmployees();
        List<Employee> employeeList = employeesResponse.getData();

        if(Optional.ofNullable(employeeList).isPresent()){

            return employeeList
                    .stream()
                    .max((o1, o2) -> Math.toIntExact(o1.getEmployee_salary() - o2.getEmployee_salary()))
                    .map(employee -> employee.getEmployee_salary().intValue()).get();
        }else{
            log.error("No Employee Data Available!");
            throw new EmployeeDoesNotExistException("No Employee Data Available!");
        }

    }

    public List<String> getTop10HighestEarningEmployeeNames() throws JsonProcessingException {
        log.debug("Calling Employee Service Client to Get Top 10 Highest Salary of employess");

        AllEmployeesResponse employeesResponse =  useMockData ? MockData.getAllEmployeeMock() :  employeeServiceClient.getAllEmployees();
        List<Employee> employeeList = employeesResponse.getData();

        if(Optional.ofNullable(employeeList).isPresent()){
            return employeeList
                    .stream()
                    .sorted((o1, o2) -> Math.toIntExact(o2.getEmployee_salary() - o1.getEmployee_salary()))
                    .map(employee -> employee.getEmployee_name())
                    .limit(10)
                    .map(salary -> salary.toString())
                    .collect(Collectors.toList());
        }else {
            log.error("No Employee Data Available!");
            throw new EmployeeDoesNotExistException("No Employee Data Available!");
        }


    }

    public Employee createEmployee(Map<String, Object> employeeInput) throws Exception {
        log.debug("Calling Employee Service Client to create employee with  {} " , employeeInput);

        EmployeeRequest employeeRequest = new ObjectMapper().convertValue(employeeInput, EmployeeRequest.class);
            EmployeeCreateResponse employeeResponse =  useMockData ?  MockData.CreateEmployee(employeeRequest) : employeeServiceClient.createEmployee(employeeRequest);

            if(Optional.ofNullable(employeeResponse.getData()).isPresent()){
                return EmployeeMapper.mapToEmployee(employeeResponse.getData(), new Employee());
        }else {
                log.error("Failed to create employee");
                throw new Exception("Failed to create employee");
            }


    }


    public String deleteEmployeeById(String id) throws Exception {
        log.debug("Calling Employee Service Client to delete employee with id  {} " , id);

        Employee employee = getEmployeeById(id);
            BaseResponse baseResponse = useMockData ?  MockData.deleteEmployById(id): employeeServiceClient.deleteEmployeeById(id);
        if(Optional.ofNullable(baseResponse.getStatus()).isPresent()){
            return employee.getEmployee_name();
        }else {
            log.error("Failed to delete employee with given Id {}",id);
            throw new Exception("Failed to delete employee with given Id "+ id);
        }

    }



}
