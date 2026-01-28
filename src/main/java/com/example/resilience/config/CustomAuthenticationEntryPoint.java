package com.example.resilience.config;

import com.example.resilience.dto.ApiError;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {

        log.info("httpRequest : {}", request);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        List<String> errors = new ArrayList<>();
        String error = "Error Occurred for " +request.getRequestURI();
        errors.add(error);
        ApiError apiError = new ApiError(HttpStatus.UNAUTHORIZED.value(), "You must log in to access this resource." ,errors);
        //response.getWriter().write(
           // "{ \"error\": \"Unauthorized\", \"message\": \"You must log in to access this resource.\" }"
        //);
        new ObjectMapper().writeValue(response.getOutputStream(),apiError);
    }
}
