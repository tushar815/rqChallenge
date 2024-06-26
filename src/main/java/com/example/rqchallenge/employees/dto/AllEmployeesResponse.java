package com.example.rqchallenge.employees.dto;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AllEmployeesResponse extends BaseResponse {
    private List<Employee> data;
}
