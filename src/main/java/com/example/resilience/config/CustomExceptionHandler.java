package com.example.resilience.config;

import com.example.resilience.dto.ApiError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class CustomExceptionHandler   {

    Logger logger = LoggerFactory.getLogger(CustomExceptionHandler.class);
    @ExceptionHandler({NoHandlerFoundException.class, NoResourceFoundException.class})
    ResponseEntity<ApiError> handleNoHandlerFoundException(Exception ex, WebRequest webRequest) {
        logger.info("exception handler : "+ ex.getMessage());
        List<String> errors = new ArrayList<>();
        String error = "No handler found for " +webRequest.getDescription(false);
        errors.add(error);
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, ex.getMessage(), errors);
        return new ResponseEntity<>(apiError, HttpStatusCode.valueOf(HttpStatus.OK.value()));
    }

    @ExceptionHandler({Exception.class})
    ResponseEntity<ApiError> globalExceptionHandler(Exception ex, WebRequest webRequest) {
        logger.info("exception handler : "+ ex.getMessage());
        List<String> errors = new ArrayList<>();
        String error = "Error Occurred for " +webRequest.getDescription(false);
        errors.add(error);
        ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), errors);
        return new ResponseEntity<>(apiError, HttpStatusCode.valueOf(HttpStatus.OK.value()));
    }
}
