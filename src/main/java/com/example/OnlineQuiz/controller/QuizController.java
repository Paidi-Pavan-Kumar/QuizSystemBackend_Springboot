package com.example.OnlineQuiz.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.OnlineQuiz.dto.AttemptQuizResponseDTO;
import com.example.OnlineQuiz.dto.QuizRequestDTO;
import com.example.OnlineQuiz.dto.QuizResponseDTO;
import com.example.OnlineQuiz.entity.User;
import com.example.OnlineQuiz.repository.UserRepository;
import com.example.OnlineQuiz.service.QuizService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/quizzes")
public class QuizController {

    private QuizService quizService;
    private UserRepository userRepository;

    public QuizController(QuizService quizService, UserRepository userRepository) {
        this.quizService = quizService;
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<QuizResponseDTO> getAll() {
        return quizService.getAll();
    }

    @GetMapping("/{id}")
    public QuizResponseDTO getQuiz(@PathVariable Long id) {
        return quizService.getQuizById(id);
    }

    @PostMapping("/{userId}/register")
    public QuizResponseDTO createQuiz(@RequestBody @Valid QuizRequestDTO dto, @PathVariable Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("user not found"));
        return quizService.createQuiz(dto, user);
    }

    @PutMapping("/{quizId}")
    public QuizResponseDTO updateQuiz(@PathVariable Long quizId, @RequestBody @Valid QuizRequestDTO dto) {
        return quizService.updateQuiz(quizId, dto);
    }

    @DeleteMapping("/{quizId}")
    public void deleteQuiz(@PathVariable Long quizId) {
        quizService.deleteQuizById(quizId);
    }

    @GetMapping("/user/{userId}")
    public List<QuizResponseDTO> getQuizzesByUser(@PathVariable Long userId) {
        return quizService.getQuizzesByUser(userId);
    }

    @GetMapping("/quiz/attempt/{quizId}")
    public ResponseEntity<AttemptQuizResponseDTO> attemptQuiz(@PathVariable Long quizId) {
        return ResponseEntity.ok(quizService.getQuizForAttempt(quizId));
    }

    // @DeleteMapping("/deleteall")
    // public void deleteQuiz() {
    // quizService.deleteAll();
    // }

}
