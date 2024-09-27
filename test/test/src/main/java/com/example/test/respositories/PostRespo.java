package com.example.test.respositories;

import com.example.test.models.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRespo extends JpaRepository<Posts,Long> {

}
