package com.example.rqchallenge.employees.dto;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;

import java.util.*;

@Data
public class AllEmployeesResponse extends BaseResponse {
    private List<Employee> data;
}
