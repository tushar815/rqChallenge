package com.example.rqchallenge.employees.dto;

import lombok.Data;

import java.util.List;

@Data
public class EmployeeResponse extends BaseResponse {

    private Employee data;
}
