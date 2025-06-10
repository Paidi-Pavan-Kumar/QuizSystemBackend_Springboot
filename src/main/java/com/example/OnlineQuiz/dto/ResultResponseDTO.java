package com.example.OnlineQuiz.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResultResponseDTO {
    private Long id;
    private String quizTitle;
    private int score;
    private int total;
    private LocalDateTime attemptedAt;
    private String username;
}