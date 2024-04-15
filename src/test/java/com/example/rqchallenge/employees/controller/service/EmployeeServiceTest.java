package com.example.rqchallenge.employees.controller.service;

import com.example.rqchallenge.employees.dto.*;
import com.example.rqchallenge.employees.exception.EmployeeDoesNotExistException;
import com.example.rqchallenge.employees.service.EmployeeService;
import com.example.rqchallenge.employees.service.EmployeeServiceClient;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {


    @Mock
    private EmployeeServiceClient employeeServiceClient;


    @InjectMocks
    private EmployeeService employeeService;


    @Test
    public void testGetAllEmployess() throws JsonProcessingException {
        List<Employee> list = Arrays.asList(
                new Employee(1,"tushar", 200L, 23, ""),
                new Employee(2,"test", 230L, 25, "")
                );
        AllEmployeesResponse allEmployeesResponse = new AllEmployeesResponse(list);
        allEmployeesResponse.setMessage("message");
        allEmployeesResponse.setStatus("success");

        when(employeeServiceClient.getAllEmployees()).thenReturn(allEmployeesResponse);

        List<Employee> employees = employeeService.getAllEmployees();

        assertThat(employees.size()).isEqualTo(2);

        assertThat(employees.get(0).getEmployee_name()).isEqualTo("tushar");
    }


    @Test
    public void testGetEmployeeById() throws JsonProcessingException {
        Employee employee =  new Employee(1,"tushar", 200L, 23, "");

        EmployeeResponse employeeResponse = new EmployeeResponse();
        employeeResponse.setData(employee);
        employeeResponse.setMessage("mesage");
        employeeResponse.setStatus("success");


        when(employeeServiceClient.getEmployeeById(anyString())).thenReturn(employeeResponse);

         Employee employees = employeeService.getEmployeeById("1");

        assertThat(employees.getEmployee_age()).isEqualTo(23);

        assertThat(employee.getEmployee_name()).isEqualTo("tushar");
    }

    @Test()
    public void testGetEmployeeByIdThrowsException() throws JsonProcessingException {

        EmployeeResponse employeeResponse = new EmployeeResponse();
        employeeResponse.setData(null);
        employeeResponse.setMessage("mesage");
        employeeResponse.setStatus("success");

        when(employeeServiceClient.getEmployeeById(anyString())).thenReturn(employeeResponse);

        EmployeeDoesNotExistException exception = assertThrows(EmployeeDoesNotExistException.class, () ->  employeeService.getEmployeeById("1"));

        assertTrue(exception.getLocalizedMessage().equals("Employee with given id 1 does not exist!"));

    }



    @Test
    public void testGetEmployeeByName() throws JsonProcessingException {
        List<Employee> list = Arrays.asList(
                new Employee(1,"tushar", 200L, 23, ""),
                new Employee(2,"test", 230L, 25, "")
        );
        AllEmployeesResponse allEmployeesResponse = new AllEmployeesResponse(list);
        allEmployeesResponse.setMessage("message");
        allEmployeesResponse.setStatus("success");


        when(employeeServiceClient.getAllEmployees()).thenReturn(allEmployeesResponse);

        List<Employee> employees = employeeService.getEmployeesByNameSearch("tushar");

        assertThat(employees.size()).isEqualTo(1);

    }

    @Test
    public void testGetEmployeeByNameThrowsException() throws JsonProcessingException {
        AllEmployeesResponse allEmployeesResponse = new AllEmployeesResponse(null);
        allEmployeesResponse.setMessage("message");
        allEmployeesResponse.setStatus("success");


        when(employeeServiceClient.getAllEmployees()).thenReturn(allEmployeesResponse);


        EmployeeDoesNotExistException e = assertThrows(EmployeeDoesNotExistException.class, () ->  employeeService.getEmployeesByNameSearch("tushar"));

        assertTrue(e.getLocalizedMessage().equals("Employee with given name tushar does not exist!"));


    }


    @Test
    public void testGetHighestSalaryOfEmployees() throws JsonProcessingException {
        List<Employee> list = Arrays.asList(
                new Employee(1, "tushar", 200L, 23, ""),
                new Employee(2, "test", 230L, 25, ""),
                new Employee(3, "Alice", 300L, 28, ""),
                new Employee(4, "Bob", 350L, 30, ""),
                new Employee(5, "Charlie", 400L, 35, ""),
                new Employee(6, "Diana", 450L, 38, ""),
                new Employee(7, "Eve", 500L, 40, ""),
                new Employee(8, "Frank", 550L, 45, ""),
                new Employee(9, "Grace", 600L, 50, ""),
                new Employee(10, "Hank", 650L, 55, ""),
                new Employee(11, "Ivy", 700L, 60, ""),
                new Employee(12, "Jack", 750L, 65, ""),
                new Employee(13, "Kate", 800L, 70, ""),
                new Employee(14, "Liam", 850L, 75, ""),
                new Employee(15, "Mia", 900L, 80, ""),
                new Employee(16, "Nick", 950L, 85, ""),
                new Employee(17, "Olivia", 1000L, 90, ""),
                new Employee(18, "Peter", 1050L, 95, ""),
                new Employee(19, "Quinn", 1100L, 100, ""),
                new Employee(20, "Rachel", 1150L, 105, ""),
                new Employee(21, "Sam", 1200L, 110, ""),
                new Employee(22, "Tessa", 1250L, 115, ""),
                new Employee(23, "Ursula", 1300L, 120, ""),
                new Employee(24, "Victor", 1350L, 125, ""),
                new Employee(25, "Wendy", 1400L, 130, ""),
                new Employee(26, "Xavier", 1450L, 135, ""),
                new Employee(27, "Yvonne", 1500L, 140, ""),
                new Employee(28, "Zach", 1550L, 145, "")
        );

        AllEmployeesResponse allEmployeesResponse = new AllEmployeesResponse(list);
        allEmployeesResponse.setMessage("message");
        allEmployeesResponse.setStatus("success");


        when(employeeServiceClient.getAllEmployees()).thenReturn(allEmployeesResponse);

        List<String> names  = employeeService.getTop10HighestEarningEmployeeNames();

        assertThat(names.size()).isEqualTo(10);
        assertThat(names.get(0).toString()).isEqualTo("Zach");


    }

    @Test
    public void testGetHighestSalaryOfEmployeesThrowsException() throws JsonProcessingException {

        AllEmployeesResponse allEmployeesResponse = new AllEmployeesResponse(null);
        allEmployeesResponse.setMessage("message");
        allEmployeesResponse.setStatus("success");


        when(employeeServiceClient.getAllEmployees()).thenReturn(allEmployeesResponse);

        EmployeeDoesNotExistException e = assertThrows(EmployeeDoesNotExistException.class, () ->  employeeService.getTop10HighestEarningEmployeeNames());

        assertTrue(e.getLocalizedMessage().equals("No Employee Data Available!"));




    }
    @Test
    public void testCreateEmplyee() throws Exception {
        EmployeeCreateResponse employeeCreateResponse = new EmployeeCreateResponse();
        employeeCreateResponse.setStatus("success");
        employeeCreateResponse.setData(new DummyEmployeeResponse("Tusahr", "200","23", 22L));

        when(employeeServiceClient.createEmployee(any())).thenReturn(employeeCreateResponse);
        Map<String ,Object> map = new HashMap<>();
        map.put("name", "tushar");
        map.put("salary", "200");
        map.put("age", "23");
        Employee employees = employeeService.createEmployee(map);

        assertThat(employees.getEmployee_name()).isEqualTo("Tusahr");

    }

    @Test
    public void testCreateEmplyeeThrowsException() throws Exception {
        EmployeeCreateResponse employeeCreateResponse = new EmployeeCreateResponse();
        employeeCreateResponse.setStatus("success");
        employeeCreateResponse.setData(null);

        when(employeeServiceClient.createEmployee(any())).thenReturn(employeeCreateResponse);
        Map<String ,Object> map = new HashMap<>();
        map.put("name", "tushar");
        map.put("salary", "200");
        map.put("age", "23");

        Exception e = assertThrows(Exception.class, () ->  employeeService.createEmployee(map));

        assertTrue(e.getLocalizedMessage().equals("Failed to create employee"));


    }
    @Test
    public void testDeleteEmployeeById() throws Exception {
        Employee employee =  new Employee(1,"tushar", 200L, 23, "");

        EmployeeResponse employeeResponse = new EmployeeResponse();
        employeeResponse.setData(employee);
        employeeResponse.setMessage("mesage");
        employeeResponse.setStatus("success");


        when(employeeServiceClient.getEmployeeById(anyString())).thenReturn(employeeResponse);


        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatus("success");
        baseResponse.setMessage("message");
        when(employeeServiceClient.deleteEmployeeById(any())).thenReturn(baseResponse);
        String response = employeeService.deleteEmployeeById("1");


        assertThat(response).isEqualTo("tushar");

    }


    @Test
    public void testDeleteEmployeeByIdThrowsException() throws Exception {
        Employee employee =  new Employee(1,"tushar", 200L, 23, "");

        EmployeeResponse employeeResponse = new EmployeeResponse();
        employeeResponse.setData(employee);
        employeeResponse.setMessage("mesage");
        employeeResponse.setStatus("success");


        when(employeeServiceClient.getEmployeeById(anyString())).thenReturn(employeeResponse);


        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatus(null);
        baseResponse.setMessage("message");
        when(employeeServiceClient.deleteEmployeeById(any())).thenReturn(baseResponse);

        Exception e = assertThrows(Exception.class, () ->  employeeService.deleteEmployeeById("1"));

        assertTrue(e.getLocalizedMessage().equals("Failed to delete employee with given Id 1"));

    }

}

