package com.example.resilience.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@ToString
public class EmployeeDTO {
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
    private String email;
    private String firstName;
    private String lastName;

}
