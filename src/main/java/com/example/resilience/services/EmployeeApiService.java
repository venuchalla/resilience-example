package com.example.resilience.services;


import com.example.resilience.annotations.LogAfterMethod;
import com.example.resilience.dto.EmployeesResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


@Service
public class EmployeeApiService {

    @Autowired
    private WebClient webClient;

    private static final Logger logger = LoggerFactory.getLogger(EmployeeApiService.class) ;

    EmployeeApiService(WebClient webClient) {
        this.webClient = webClient;
    }


    @LogAfterMethod(value = "getEmployees")
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
