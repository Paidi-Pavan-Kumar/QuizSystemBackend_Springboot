package com.example.OnlineQuiz.dto;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QuizRequestDTO {

    @NotBlank(message = "title cannot be empty")
    private String title;

    @NotBlank(message = "description cannot be empty")
    private String description;

    @NotEmpty(message = "Quiz must contain at least one question")
    private List<@Valid QuestionRequestDTO> questions;
}
