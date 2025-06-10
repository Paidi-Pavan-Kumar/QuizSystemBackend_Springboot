package com.example.OnlineQuiz.dtoMappers;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.example.OnlineQuiz.dto.ResultResponseDTO;
import com.example.OnlineQuiz.entity.Quiz;
import com.example.OnlineQuiz.entity.Result;
import com.example.OnlineQuiz.entity.User;

@Component
public class ResultDTOMapper {

    public ResultResponseDTO toResponseDTO(Result result) {
        ResultResponseDTO dto = new ResultResponseDTO();
        dto.setId(result.getId());
        dto.setQuizTitle(result.getQuiz().getTitle());
        dto.setScore(result.getScore());
        dto.setTotal(result.getTotal());
        dto.setAttemptedAt(result.getAttemptedAt());
        dto.setUsername(result.getUser().getUsername());
        return dto;
    }

    public Result toEntity(User user, Quiz quiz) {
        Result result = new Result();
        result.setAttemptedAt(LocalDateTime.now());
        result.setUser(user);
        result.setQuiz(quiz);
        return result;
    }
}
