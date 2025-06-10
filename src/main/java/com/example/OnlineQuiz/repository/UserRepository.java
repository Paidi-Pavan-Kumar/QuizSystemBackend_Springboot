package com.example.OnlineQuiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.OnlineQuiz.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
}