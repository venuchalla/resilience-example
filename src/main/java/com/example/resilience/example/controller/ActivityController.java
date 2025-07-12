package com.example.resilience.example.controller;

import com.example.resilience.example.services.BoredApiService;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = "/activity")
public class ActivityController {
   private  final Logger logger = LoggerFactory.getLogger(ActivityController.class);
    @Autowired
    BoredApiService boredApiService;
    @GetMapping(path = "/{type}")
    @CircuitBreaker(name = "BoredApiCircuitBreaker" ,fallbackMethod = "BoredApiFallBackMethod" )
    public ResponseEntity<String> getActivityByType(@PathVariable String type){
        logger.info("path variable : {}" ,type);
       String result= boredApiService.getActivityByType(type).block();
        return new ResponseEntity<>(result, HttpStatusCode.valueOf(200));

    }

    @GetMapping(path = "/")
    public ResponseEntity<String> getActivity(){
        String result= boredApiService.getActivity().block();
        return new ResponseEntity<>(result, HttpStatusCode.valueOf(200));

    }

    @GetMapping(path = "/fallback/{status}")

    public Mono<String> getActivityFallBack(@PathVariable String status){
        logger.info("path variable : {}" ,status);
         return boredApiService.getActivityFallBack(status);
       // return new ResponseEntity<>(result, HttpStatusCode.valueOf(200));

    }

}
