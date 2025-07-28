package com.example.resilience.controller;

import com.example.resilience.dto.CommentVO;
import com.example.resilience.dto.Product;
import com.example.resilience.services.CommentApiService;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/products")
@Tags( value ={
        @Tag(name ="Product Controller")
} )
public class ProductController {
@Autowired
    CommentApiService commentApiService;
    @GetMapping(path = "", produces = MediaType.ALL_VALUE)
    public ResponseEntity<String> PrContoller() {
        return new ResponseEntity<>("welcome to product api", HttpStatus.OK);
    }
    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Product> getProductInformation(@PathVariable String id) throws Exception {
        Product p = Product.builder().name("SavingsBuilder").id("011").build();
        return new ResponseEntity<>(p, HttpStatus.OK);
    }
    @GetMapping(path = "/comments", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<CommentVO>> getJsonPlaceHolder() {
        //https://jsonplaceholder.typicode.com/comments
        List<CommentVO> commentVOList = commentApiService.getComments();
        return new ResponseEntity<>(commentVOList,HttpStatus.OK);

    }
}
