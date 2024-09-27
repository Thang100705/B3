package com.example.test.services;
import com.example.test.Dto.PostDto;
import com.example.test.models.Posts;
import com.example.test.respositories.PostRespo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRespo postRespo;


    public PostService(PostRespo postRespo) {
        this.postRespo = postRespo;
    }

    @Transactional
    public List<Posts>getAll(){
        return postRespo.findAll();
    }
    @Transactional
    public Optional<Posts>getById(Long id){
        return postRespo.findById(id);
    }
    @Transactional
    public Posts save(Posts post) {
        if (post.getPost_id() == null) {
            post.setCreated_at(LocalDateTime.now());
        }
        post.setUpdated_at(LocalDateTime.now());
        return postRespo.save(post);
    }

    @Transactional
    public void deleteById(Long id){
        postRespo.deleteById(id);
    }


}
