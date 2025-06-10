package com.example.OnlineQuiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.OnlineQuiz.entity.Quiz;

import java.util.List;

public interface QuizRepository extends JpaRepository<Quiz, Long> {
    List<Quiz> findByCreatedById(Long userId);
}
