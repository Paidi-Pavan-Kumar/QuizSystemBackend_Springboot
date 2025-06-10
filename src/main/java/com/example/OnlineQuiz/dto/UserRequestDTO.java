package com.example.OnlineQuiz.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDTO {

    @NotBlank(message = "username cannot be empty")
    private String username;

    @Email(message = "email should be valid")
    @Column(unique = true, nullable = false)
    private String email;

    @NotBlank(message = "password cannot be null")
    private String password;
    
    private String role;
}
