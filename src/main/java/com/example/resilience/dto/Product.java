package com.example.resilience.dto;


import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
public class Product {
    private String id;
    private String name;

}
