package com.example.resilience.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class Comment {

    private int postId;
    private int id;
    private String name;
    private String email;
    private String body;
}
