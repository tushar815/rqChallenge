package com.example.rqchallenge.employees.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    private long id;
    private String employee_name;
    private Long employee_salary;
    private int employee_age;
    private String profile_image;



}
