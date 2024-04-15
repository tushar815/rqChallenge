package com.example.rqchallenge.employees.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DummyEmployeeResponse {

    private String name;
    private String salary;
    private String age;
    private Long id;

}
