package com.example.rqchallenge.employees.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeCreateResponse {

    private String status;
    private DummyEmployeeResponse data;
}
