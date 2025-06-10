package com.example.OnlineQuiz.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class QuizResponseDTO {
    private Long id;
    private String title;
    private String description;
    private LocalDateTime createdAt;
    private Long createdBy;
    private List<QuestionResponseDTO> questions;
}
