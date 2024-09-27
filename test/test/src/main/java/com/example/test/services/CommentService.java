package com.example.test.services;

import com.example.test.models.Comments;
import com.example.test.respositories.CommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    private CommentRepo commentRepo;

    @Autowired
    public CommentService(CommentRepo commentRepo) {
        this.commentRepo = commentRepo;
    }
    @Transactional
    public List<Comments>getAll(){
        return commentRepo.findAll();
    }
    @Transactional
    public Optional<Comments> getByIdComment(Long id){
        return commentRepo.findById(id);
    }
    @Transactional
    public Comments Create(Comments comments){
        if(comments.getComment_id()==null){
            comments.setCreated_at(LocalDateTime.now());
        }
        return commentRepo.save(comments);
    }
    @Transactional
    public void deleteCommentId(Long id){
        commentRepo.deleteById(id);
    }
}
