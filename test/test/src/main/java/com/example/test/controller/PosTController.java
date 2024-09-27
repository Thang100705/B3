package com.example.test.controller;

import com.example.test.models.Posts;
import com.example.test.services.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api")
public class PosTController {
    @Autowired
    private PostService postService;

    public PosTController(PostService postService) {
        this.postService = postService;
    }
    @PostMapping("/posts")
    public ResponseEntity<Posts>CreateBlog(@Valid @RequestBody Posts post){
        try {
            Posts create = postService.save(post);
            return new ResponseEntity<>(create, HttpStatus.CREATED);
        }catch (Exception e){
            return ResponseEntity.status((HttpStatus.INTERNAL_SERVER_ERROR)).build();
        }
    }
    @PutMapping("/posts")
    public ResponseEntity<Posts>UpdateBlog(@Valid @RequestBody Posts post) {
        try {
            Posts update = postService.save(post);
            return ResponseEntity.ok(update);
        } catch (Exception e) {
            return ResponseEntity.status((HttpStatus.INTERNAL_SERVER_ERROR)).build();
        }
    }
}
