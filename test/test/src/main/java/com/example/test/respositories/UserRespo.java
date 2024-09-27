package com.example.test.respositories;

import com.example.test.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRespo extends JpaRepository<Users,Long> {
    Optional<Users> findByUsername(String username);
}
