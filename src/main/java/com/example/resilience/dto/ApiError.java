package com.example.resilience.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;



@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ApiError {
    private int statusCode;
    private String message;
    private List<String> errors;

}
