package com.example.OnlineQuiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.OnlineQuiz.entity.Question;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findByQuizId(Long quizId);
}
