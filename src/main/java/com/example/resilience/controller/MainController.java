package com.example.resilience.controller;

import com.example.resilience.dto.UserJwtResponse;
import com.example.resilience.dto.UserRequest;
import com.example.resilience.dto.UserResponse;
import com.example.resilience.entities.Role;
import com.example.resilience.entities.User;
import com.example.resilience.repository.UsersRepository;
import com.example.resilience.services.PaymentService;
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
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;
import java.util.Optional;

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

    @Autowired
    PaymentService paymentService;
    private final Logger logger = LoggerFactory.getLogger(MainController.class);

    @GetMapping(path = "", produces = MediaType.ALL_VALUE)
    public ResponseEntity<String> Hello() {

        return new ResponseEntity<>("Hello world", HttpStatus.OK);
    }
    @GetMapping(path = "/hello/{status}", produces = MediaType.ALL_VALUE)
    public ResponseEntity<String> HelloStatus( @PathVariable String status) {
        if(Objects.equals(status, "404")){
       throw new ResponseStatusException(HttpStatus.NOT_FOUND,"user not found");
        }
        return new ResponseEntity<>("Hello world  status : "+ status, HttpStatus.OK);
    }


    @PostMapping(path = "/signin", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserJwtResponse> authenticate(@RequestBody UserRequest user) {
        try {
            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
            logger.info("authentication : {} password : {}", authentication.isAuthenticated(), authentication.getPrincipal());
            String token = jwtUtil.generateToken(authentication.getName());
            UserJwtResponse userJwtResponse = new UserJwtResponse(authentication.getName(), token);
            return new ResponseEntity<>(userJwtResponse , HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Exception occurred  : {}", e.getLocalizedMessage());
            throw e;
        }


    }

    @PostMapping(path = "/signup", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResponse> signup(@RequestBody UserRequest userRq) {
        Optional<User> eUser = usersRepository.findByUserName(userRq.getUsername());
        if(eUser.isPresent()){
            User user = eUser.get();
            UserResponse userResponse = new UserResponse(user.getUsername(),user.getRole().name());
            return new ResponseEntity<>(userResponse,HttpStatus.OK);
        }
        User user = new User();
        user.setUserName(userRq.getUsername());
        user.setPassword(passwordEncoder.encode(userRq.getPassword()));
        Role role = "admin".equals(userRq.getUsername()) ? Role.ROLE_ADMIN : Role.ROLE_USER;
        user.setRole(role);
        User nUser = usersRepository.save(user);
        UserResponse userResponse = new UserResponse(nUser.getUsername(),user.getRole().name());
        return new ResponseEntity<>(userResponse, HttpStatus.CREATED);

    }

    @GetMapping(path = "/hello/id")
public ResponseEntity<String> paymentServiceTest(){
       String s = paymentService.paymentService();
       return  new ResponseEntity<>(s,HttpStatus.OK);
    }

}
