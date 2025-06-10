package com.example.OnlineQuiz.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OptionRequestDTO {

    @NotBlank(message = "Option text cannot be blank")
    private String text;

    // No need for validation on `correct` unless you want business rules like "at least one must be true"
    private boolean correct;
}
