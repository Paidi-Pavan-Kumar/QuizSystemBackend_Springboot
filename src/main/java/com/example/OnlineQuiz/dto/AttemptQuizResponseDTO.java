package com.example.OnlineQuiz.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AttemptQuizResponseDTO {
    private Long quizId;
    private String title;
    private String description;
    private List<AttemptQuestionDTO> questions;
}
