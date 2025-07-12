package com.example.resilience.example.controller;


import com.example.resilience.example.dto.Employee;
import com.example.resilience.example.dto.EmployeesResponse;
import com.example.resilience.example.services.EmployeeApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);
    @Autowired
    EmployeeApiService employeeApiService;

    @GetMapping("/")
    ResponseEntity<List<Employee>> getEmployees() {
        logger.info("getEmployees method");
        Mono<EmployeesResponse> employees =employeeApiService.getEmployees();
        List<Employee> result = Objects.requireNonNull(employees.block()).getData();
        return new ResponseEntity<>(result, HttpStatus.OK);

    }
}
