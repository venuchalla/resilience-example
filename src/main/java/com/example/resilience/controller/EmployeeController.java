package com.example.resilience.controller;


import com.example.resilience.dto.EmployeeDTO;
import com.example.resilience.dto.EmployeesResponse;
import com.example.resilience.services.EmployeeApiService;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.SecurityContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/employees")
@Tags(value = {
        @Tag(name = "Employee Controller")
})
public class EmployeeController {
    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);
    @Autowired
    EmployeeApiService employeeApiService;

    @GetMapping("")
    ResponseEntity<List<EmployeeDTO>> getEmployees() {
        logger.info("getEmployees method");
        Mono<EmployeesResponse> employees = employeeApiService.getEmployees();
        //employees.s
        List<EmployeeDTO> result = Objects.requireNonNull(employees.block()).getData();
        return new ResponseEntity<>(result, HttpStatus.OK);

    }

    @GetMapping("/All")
    ResponseEntity<List<EmployeeDTO>> getEmployeesUsingRepo() {
        logger.info("get all employees");
        EmployeesResponse result = employeeApiService.getAllEmployees();
        return  new ResponseEntity<>(result.getData(),HttpStatus.OK);
    }
}
