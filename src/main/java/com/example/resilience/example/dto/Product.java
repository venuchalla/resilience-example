package com.example.resilience.example.dto;


import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
public class Product {
    private String id;
    private String name;

}
