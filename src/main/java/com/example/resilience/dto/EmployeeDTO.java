package com.example.resilience.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@ToString
@Schema(name = "Employee")
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
    @JsonProperty(namespace = "email")
    private String email;
    @JsonProperty(namespace = "firstName")
    private String firstName;
    @JsonProperty(namespace = "lastName")
    private String lastName;

}
