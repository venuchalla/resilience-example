package com.example.resilience.example.clients;

import com.example.resilience.example.response.Comment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;

//https://jsonplaceholder.typicode.com/comments
@FeignClient(name = "comments", url = "https://jsonplaceholder.typicode.com")
public interface CommentsClient {

    @GetMapping("/comments")
    public ArrayList<Comment> getComments();
}
