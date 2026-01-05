package com.example.resilience.services;


import com.example.resilience.annotations.LogAfterMethod;
import com.example.resilience.dto.EmployeeDTO;
import com.example.resilience.dto.EmployeesResponse;
import com.example.resilience.entities.Employee;
import com.example.resilience.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class EmployeeApiService {

    @Autowired
    private WebClient webClient;

    @Autowired
    private EmployeeRepository employeeRepository;

    private static final Logger logger = LoggerFactory.getLogger(EmployeeApiService.class) ;

    EmployeeApiService(WebClient webClient) {
        this.webClient = webClient;
    }


    @LogAfterMethod(value = "getEmployees")
    public Mono<EmployeesResponse> getEmployees() {


        return webClient
                .get()
                .uri("employees")
                .retrieve()
                .onStatus(httpStatusCode -> !httpStatusCode.is2xxSuccessful(),
                        clientResponse -> Mono.error(new Exception("EmployeeNotFound")))
                .bodyToMono(EmployeesResponse.class)
                .onErrorResume(Exception.class,e -> Mono.empty());

    }

    public EmployeesResponse getAllEmployees(){
        List<Employee> results = employeeRepository.findAll();
    //List<EmployeesResponse> employeesResponses = new ArrayList<>();
       List<EmployeeDTO> employeeDTOS =  results.stream().map(e -> {
                    EmployeeDTO emplyeeDTO = new EmployeeDTO();
                    emplyeeDTO.setId(e.getEmployeeId());
                    emplyeeDTO.setEmail(e.getEmail());
                    emplyeeDTO.setFirstName(e.getFirstName());
                    emplyeeDTO.setLastName(e.getLastName());
                    return  emplyeeDTO;
                }
        ).toList();
       return  new EmployeesResponse(employeeDTOS,"200","SUCCESS");
    }

}
