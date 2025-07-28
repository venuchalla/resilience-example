package com.example.resilience.controller;

import com.example.resilience.dto.UserRequest;
import com.example.resilience.dto.UserResponse;
import com.example.resilience.entities.User;
import com.example.resilience.repository.UsersRepository;
import com.example.resilience.util.JwtUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@Tags(value = {
        @Tag(name = "Main Controller")
})
public class MainController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UsersRepository usersRepository;
    @Autowired
    JwtUtil jwtUtil;
    @Autowired
    PasswordEncoder passwordEncoder;
    private final Logger logger = LoggerFactory.getLogger(MainController.class);

    @GetMapping(path = "", produces = MediaType.ALL_VALUE)
    public ResponseEntity<String> Hello() {

        return new ResponseEntity<>("Hello world", HttpStatus.OK);
    }

    @PostMapping(path = "/signin", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResponse> authenticate(@RequestBody UserRequest user) {
        try {
            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
            logger.info("authentication : {} password : {}", authentication.isAuthenticated(), authentication.getPrincipal());
            String token = jwtUtil.generateToken(authentication.getName());
            UserResponse userResponse = new UserResponse(authentication.getName(), token);
            return new ResponseEntity<>(userResponse, HttpStatus.OK);
        } catch (Exception e) {
            throw e;
        }


    }

    @PostMapping(path = "/signup", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserRequest> signup(@RequestBody UserRequest userRq) {
        User user = new User();
        user.setUserName(userRq.getUsername());
        user.setPassword(passwordEncoder.encode(userRq.getPassword()));
        User eUser = usersRepository.save(user);
        return new ResponseEntity<>(userRq, HttpStatus.OK);

    }


}
