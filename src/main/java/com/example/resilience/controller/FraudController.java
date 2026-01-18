package com.example.resilience.controller;


import com.example.resilience.dto.Transaction;
import com.example.resilience.services.FraudDetectionService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fraud")
public class FraudController {

    private final FraudDetectionService fraudDetectionService;

    public FraudController(FraudDetectionService fraudDetectionService){
        this.fraudDetectionService = fraudDetectionService;
    }

    @GetMapping(value = "")
    public String ping(){
        return "pong";
    }

    @PostMapping(value = "/check",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public Transaction checkFraud(@RequestBody Transaction transaction){
        return fraudDetectionService.evaluate(transaction);
    }
}
