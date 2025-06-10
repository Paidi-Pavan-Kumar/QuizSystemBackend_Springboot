package com.example.OnlineQuiz.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.OnlineQuiz.dto.ResultRequestDTO;
import com.example.OnlineQuiz.dto.ResultResponseDTO;
import com.example.OnlineQuiz.dtoMappers.ResultDTOMapper;
import com.example.OnlineQuiz.entity.Option;
import com.example.OnlineQuiz.entity.Quiz;
import com.example.OnlineQuiz.entity.Result;
import com.example.OnlineQuiz.entity.User;
import com.example.OnlineQuiz.repository.OptionRepository;
import com.example.OnlineQuiz.repository.QuizRepository;
import com.example.OnlineQuiz.repository.ResultRepository;
import com.example.OnlineQuiz.repository.UserRepository;

@Service
public class ResultService {

    @Autowired
    private ResultRepository resultRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private ResultDTOMapper resultDTOMapper;

    @Autowired
    private OptionRepository optionRepository;

    public ResultResponseDTO saveResult(ResultRequestDTO dto, Long userId) {
        // Step 1: Get user & quiz
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Quiz quiz = quizRepository.findById(dto.getQuizId())
                .orElseThrow(() -> new RuntimeException("Quiz not found"));

        // Step 2: Get selected options
        List<Option> selectedOptions = optionRepository.findAllById(dto.getSelectedOptionIds());

        // Step 3: Calculate score
        int score = 0;
        for (Option option : selectedOptions) {
            if (option.isCorrect()) score++;
        }

        // Step 4: Total = number of questions in quiz
        int total = quiz.getQuestions().size();

        // Step 5: Create and save result
        Result result = resultDTOMapper.toEntity(user, quiz);
        result.setScore(score);
        result.setTotal(total);

        resultRepository.save(result);

        return resultDTOMapper.toResponseDTO(result);
    }

    public List<ResultResponseDTO> getResultsByUserId(Long userId) {
        return resultRepository.findByUserId(userId)
                .stream()
                .map(resultDTOMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public List<ResultResponseDTO> getAllResults() {
        return resultRepository.findAll()
                .stream()
                .map(resultDTOMapper::toResponseDTO)
                .collect(Collectors.toList());
    }
}