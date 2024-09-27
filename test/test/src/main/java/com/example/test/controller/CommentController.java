package com.example.test.controller;

import com.example.test.models.Comments;
import com.example.test.services.CommentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CommentController {
    private CommentService commentService;
    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/comment")
    public List<Comments> showComment() {
        return commentService.getAll();
    }
    @PostMapping
    public ResponseEntity<Comments>createComment(@Valid @PathVariable Comments comments){
        try {
            Comments create = commentService.Create(comments);
            return new ResponseEntity<>(create, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.status((HttpStatus.INTERNAL_SERVER_ERROR)).build();
        }
    }
    @PutMapping("/comment")
    public ResponseEntity<Comments>UpdateComment(@Valid @RequestBody Comments comments) {
        try {
            Comments update = commentService.Create(comments);
            return ResponseEntity.ok(update);
        } catch (Exception e) {
            return ResponseEntity.status((HttpStatus.INTERNAL_SERVER_ERROR)).build();
        }
    }
    @DeleteMapping("/comment/{id}")
    public ResponseEntity<Void>deleteCommentId(@PathVariable Long id){
        Optional<Comments> category = commentService.getByIdComment(id);
        if (category.isPresent()) {
            commentService.deleteCommentId(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
