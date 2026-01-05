package com.example.resilience.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name = "employee")
public class Employee {
    String firstName;
    String lastName;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long employeeId;
    String email;

}
