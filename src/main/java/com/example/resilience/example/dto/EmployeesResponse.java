package com.example.resilience.example.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class EmployeesResponse {
    @JsonProperty(namespace = "data")
    private List<Employee> data;
    @JsonProperty(namespace = "status")
   private String status;
    @JsonProperty(namespace = "message")
   private String message;
}
