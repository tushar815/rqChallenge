package com.example.rqchallenge.employees.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class EmployeeDoesNotExistException extends RuntimeException{

    public EmployeeDoesNotExistException(String message){
        super(message);
    }
}
