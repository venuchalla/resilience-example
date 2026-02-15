package com.example.resilience.controller;


import com.example.resilience.dto.Option;
import com.example.resilience.dto.Options;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/props")
public class PropertiesController {


    @GetMapping(path = "/getResidencyType",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Option>> getResidencyType(){

        Option firstOption = new Option("Rent","rent");
        Option secondOption = new Option("Own","own");
        Option thirdOption = new Option("Other","other");
        List<Option> options = new ArrayList<>();
        options.add(firstOption);
        options.add(secondOption);
        options.add(thirdOption);
        return new ResponseEntity<>(options, HttpStatusCode.valueOf(200));

    }


}
