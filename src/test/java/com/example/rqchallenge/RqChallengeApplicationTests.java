package com.example.rqchallenge;

import com.example.rqchallenge.employees.dto.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
class RqChallengeApplicationTests {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void contextLoads() {
    }


    @Test
    void whenGetAllEmployeesCalled(){

        webTestClient
                .get()
                .uri("/employee")
                .exchange()
                .expectStatus().isOk()
                .expectBody(Employee.class).value( employee -> {
                    assertThat(employee).isNotNull();
                });
    }



    private List<Employee> employeeList() {
    ArrayList<Employee> list = new ArrayList<>();
        Employee employee = new Employee(10, "Tushar", 200L, 23, "");
        list.add(employee);
        return list;
    }
}
