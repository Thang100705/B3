package com.example.test.respositories;


import com.example.test.models.Images;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRespo extends JpaRepository<Images,Long> {

}
