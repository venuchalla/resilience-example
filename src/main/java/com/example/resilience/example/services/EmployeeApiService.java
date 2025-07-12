package com.example.resilience.example.services;


import com.example.resilience.example.dto.Employee;
import com.example.resilience.example.dto.EmployeesResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Function;
import java.util.function.Predicate;


@Service
public class EmployeeApiService {

    @Autowired
    private WebClient webClient;

    private static final Logger logger = LoggerFactory.getLogger(EmployeeApiService.class) ;

    EmployeeApiService(WebClient webClient) {
        this.webClient = webClient;
    }


    public Mono<EmployeesResponse> getEmployees() {
        logger.info("employee service class");
        return webClient
                .get()
                .uri("employees")
                .retrieve()
                .onStatus(httpStatusCode -> !httpStatusCode.is2xxSuccessful(),
                        clientResponse -> Mono.error(new Exception("EmployeeNotFound")))
                .bodyToMono(EmployeesResponse.class)
                .onErrorResume(Exception.class,e -> Mono.empty());

    }


}
