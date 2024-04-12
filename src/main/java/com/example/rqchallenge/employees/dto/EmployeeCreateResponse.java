package com.example.rqchallenge.employees.dto;

import lombok.Data;

@Data
public class EmployeeCreateResponse {

    private String status;
    private DummyEmployeeResponse data;
}
