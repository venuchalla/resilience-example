package com.example.resilience.example.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
    private final Logger logger = LoggerFactory.getLogger(MainController.class);

    @GetMapping(path = "/", produces = MediaType.ALL_VALUE)
    public ResponseEntity<String> Hello() {
        logger.info("status in main controller");
        return new ResponseEntity<>("Hello world", HttpStatus.OK);
    }

    @GetMapping("/welcome")
    public String welcomeMethod() {
        return "welcome to demo controller";
    }
    @GetMapping("/hello")
    public String helloMethod() {
        return "Hello User";
    }

}
