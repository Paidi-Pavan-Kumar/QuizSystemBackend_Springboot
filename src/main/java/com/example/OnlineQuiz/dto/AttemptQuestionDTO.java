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
public class AttemptQuestionDTO {
    private Long questionId;
    private String content;
    private List<AttemptOptionDTO> options;
}
