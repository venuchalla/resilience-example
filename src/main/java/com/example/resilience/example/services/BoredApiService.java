package com.example.resilience.example.services;


import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.ReactiveResilience4JCircuitBreakerFactory;
import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreakerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.DefaultUriBuilderFactory;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.LinkedList;
import java.util.function.Consumer;


@Service
public class BoredApiService {
    private Logger logger = LoggerFactory.getLogger(BoredApiService.class);
    private final WebClient webClient;
    private  DefaultUriBuilderFactory defaultUriBuilderFactory;
    private final String path="api/activity";
    private final String localHostUrl = "http://localhost:8080/";



    public BoredApiService(WebClient webClient,DefaultUriBuilderFactory defaultUriBuilderFactory) {
        this.webClient = webClient;
        this.defaultUriBuilderFactory =defaultUriBuilderFactory;
       // this.reactiveCircuitBreakerFactory= reactiveCircuitBreakerFactory;
    }


    public Mono<String> getActivity(){
        //URI uri = new DefaultUriBuilderFactory(path+"/").builder().build();
        URI uri = defaultUriBuilderFactory.builder().path(path+"/").build();
        logger.info("path variable : "+ uri.getPath());
        return  webClient.get().uri(uri).retrieve().bodyToMono(String.class);
    }

    public Mono<String> getActivityByType(String type) {
        URI uri = defaultUriBuilderFactory.builder().path(path+"/").queryParam("type",type).build();
        logger.info("using query param" + uri.getPath());
        return webClient.get().uri(uri).retrieve().bodyToMono(String.class);
    }

   @CircuitBreaker(name = "BoredApiCircuitBreaker", fallbackMethod = "BoredApiFallBackMethod")
    public Mono<String> getActivityFallBack(String status) {
        logger.info("status : {}", status);
        return WebClient.create(localHostUrl).get().uri("hello/{status}", status)
                .retrieve()
                .bodyToMono(String.class);
    }

    public Mono<String>  BoredApiFallBackMethod(Throwable throwable){
        logger.info( "msg :{} ",throwable.getLocalizedMessage());
        return Mono.just("server is down");
    }

}
