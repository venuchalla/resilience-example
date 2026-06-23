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
@Table(name = "Properties")
public class Properties {
    String propName;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long propId;
    String propValue;
    //E propValue;
}
