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
public class QuestionRequestDTO {

    @NotBlank(message = "Question content cannot be blank")
    private String content;

    @NotEmpty(message = "Question must have at least one option")
    private List<@Valid OptionRequestDTO> options;
}
