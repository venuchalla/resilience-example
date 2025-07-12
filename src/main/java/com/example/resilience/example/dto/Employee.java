package com.example.resilience.example.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class Employee {
    @JsonProperty(namespace = "id")
    private long id;
    @JsonProperty(namespace = "employee_name")
    private String employee_name;
    @JsonProperty(namespace = "employee_salary")
    private String employee_salary;
    @JsonProperty(namespace = "employee_age")
    private String employee_age;
    @JsonProperty(namespace = "profile_image")
    private String profile_image;
}
