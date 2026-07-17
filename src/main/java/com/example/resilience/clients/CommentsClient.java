package com.example.resilience.clients;

import com.example.resilience.response.Comment;
import java.util.ArrayList;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

// https://jsonplaceholder.typicode.com/comments
@FeignClient(name = "comments", url = "https://jsonplaceholder.typicode.com")
public interface CommentsClient {

  @GetMapping("/comments")
  public ArrayList<Comment> getComments();
}
