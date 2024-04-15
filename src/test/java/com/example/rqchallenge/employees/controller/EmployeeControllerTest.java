package com.example.rqchallenge.employees.controller;

import com.example.rqchallenge.employees.EmployeeController;
import com.example.rqchallenge.employees.dto.Employee;
import com.example.rqchallenge.employees.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;




    @Test
    public void ReturnSuccessWhenGetAllEmployeesCalled() throws Exception {

        List<Employee> employees = Arrays.asList(
                new Employee(1, "Tushar", 200L, 29, ""),
                new Employee(2, "Kalbhor",300L, 29, "-")
        );
        when(employeeService.getAllEmployees()).thenReturn(employees);
         mockMvc.perform(MockMvcRequestBuilders
                .get("/employee"))
                .andExpect(status().isOk())
                 .andExpect(jsonPath("$").isArray())
                 .andExpect(jsonPath("$[0].id").value(1));
    }


    @Test
    public void ReturnSuccessWhenGetEmployeeByIdCalled() throws Exception {
        String id = "1";
        Employee employee =  new Employee(1, "Tushar", 200L, 29, "");
;
        when(employeeService.getEmployeeById(anyString())).thenReturn(employee);
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/employee/{id}",1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }


    @Test
    public void ReturnSuccessWhengetEmployeesByNameSearchCalled() throws Exception {
        Employee employee =  new Employee(1, "Tushar", 200L, 29, "");;
        List<Employee> employees = Arrays.asList(employee);
        when(employeeService.getEmployeesByNameSearch(anyString())).thenReturn(employees);
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/employee/search/{searchString}","Tushar"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").value(1));
    }

    @Test
    public void ReturnSuccessWhenGetHighestSalaryOfEmployees() throws Exception {
        Integer high = 329000;
        when(employeeService.getHighestSalaryOfEmployees()).thenReturn(high);
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/employee/highestSalary"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(329000));
    }


    @Test
    public void ReturnSuccessWhenGetTopTenHighestEarningEmployeeNames() throws Exception {
        List<String> salaries = Arrays.asList(
               "433399",
                "433398");
        when(employeeService.getTop10HighestEarningEmployeeNames()).thenReturn(salaries);
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/employee/topTenHighestEarningEmployeeNames"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0]").value(433399));

    }

    @Test
    public void ReturnSuccessCreateEmployeeCalled() throws Exception {
        Employee employee =  new Employee(200, "Tushar", 123L, 23, "");;

        when(employeeService.createEmployee(any())).thenReturn(employee);
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/employee")
                .content("{\"name\":\"Tushar\",\"salary\":\"123\",\"age\":\"23\"}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(200))
                .andExpect(jsonPath("$.employee_name").value("Tushar"));

    }

    @Test
    public void ReturnSuccessDeleteEmployeeByIdCalled() throws Exception {

        when(employeeService.deleteEmployeeById(any())).thenReturn("Tushar");
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/employee/{id}", 1))
                .andExpect(status().isNoContent())
                .andExpect(jsonPath("$").value("Tushar"));

    }
}
