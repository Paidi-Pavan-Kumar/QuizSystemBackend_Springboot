package com.example.OnlineQuiz.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.OnlineQuiz.dto.AttemptQuizResponseDTO;
import com.example.OnlineQuiz.dto.QuizRequestDTO;
import com.example.OnlineQuiz.dto.QuizResponseDTO;
import com.example.OnlineQuiz.dtoMappers.QuizDTOMapper;
import com.example.OnlineQuiz.entity.Quiz;
import com.example.OnlineQuiz.entity.User;
import com.example.OnlineQuiz.exceptions.ResourceNotFoundException;
import com.example.OnlineQuiz.repository.QuizRepository;

@Service
public class QuizService {

    @Autowired
    private QuizRepository quizRepository;

    public QuizResponseDTO createQuiz(QuizRequestDTO quiz, User user) {
        return QuizDTOMapper.toDTO(quizRepository.save(QuizDTOMapper.toEntity(quiz, user)));
    }

    public List<QuizResponseDTO> getAll() {
        return quizRepository.findAll()
                .stream()
                .map(QuizDTOMapper::toDTO)
                .toList();
    }

    public AttemptQuizResponseDTO getQuizForAttempt(Long quizId) {
        return quizRepository.findById(quizId)
                .map(QuizDTOMapper::toAttemptDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Quiz not found with id"  + quizId));
    }

    public QuizResponseDTO getQuizById(Long id) {
        return quizRepository.findById(id)
                .map(QuizDTOMapper::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Quiz not found with ID: " + id));
    }

    public void deleteAll() {
        quizRepository.deleteAll();
    }

    public QuizResponseDTO updateQuiz(Long quizId, QuizRequestDTO dto) {
        Quiz existingQuiz = quizRepository.findById(quizId)
                .orElseThrow(() -> new ResourceNotFoundException("Quiz not found with ID: " + quizId));

        existingQuiz.setTitle(dto.getTitle());
        existingQuiz.setDescription(dto.getDescription());
        //existingQuiz.setQuestions(QuizDTOMapper.toEntity(dto.getQuestions(), existingQuiz));
        return QuizDTOMapper.toDTO(quizRepository.save(existingQuiz));
    }

    public void deleteQuizById(Long quizId) {
        Quiz quiz = quizRepository.findById(quizId)
                .orElseThrow(() -> new ResourceNotFoundException("Quiz not found with ID: " + quizId));
        quizRepository.deleteById(quizId);
    }

    public List<QuizResponseDTO> getQuizzesByUser(Long userId) {
        List<QuizResponseDTO> quizzes = quizRepository.findByCreatedById(userId)
                .stream()
                .map(QuizDTOMapper::toDTO)
                .toList();

        if (quizzes.isEmpty()) {
            throw new ResourceNotFoundException("No quizzes found for user ID: " + userId);
        }
        return quizzes;
    }

}
