package com.example.rqchallenge.employees.exception;


import com.example.rqchallenge.employees.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {



    @ExceptionHandler(EmployeeDoesNotExistException.class)
    public ResponseEntity<ErrorResponse> handleEmployeeDoesNotExistException(EmployeeDoesNotExistException e
    , WebRequest webRequest){

        ErrorResponse errorResponse = new ErrorResponse(
        webRequest.getDescription(false),
        HttpStatus.NOT_FOUND,
        e.getMessage(),
        LocalDateTime.now()
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }


}
