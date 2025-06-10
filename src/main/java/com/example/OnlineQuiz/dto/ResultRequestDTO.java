package com.example.OnlineQuiz.dto;

import java.util.List;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResultRequestDTO {

    @NotNull(message = "Quiz ID is required")
    private Long quizId;

    private List<Long> selectedOptionIds;
}




