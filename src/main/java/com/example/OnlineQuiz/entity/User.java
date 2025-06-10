package com.example.OnlineQuiz.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Username is required")
    private String username;

    @Email(message = "email should be valid")
    @Column(unique = true, nullable = false)
    private String email;

    @NotBlank(message = "password is required")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "enum('ROLE_ADMIN','ROLE_USER')")
    private Role role;

    // Quizzes created by the user (if role is ADMIN)
    @OneToMany(mappedBy = "createdBy", cascade = CascadeType.ALL)
    private List<Quiz> createdQuizzes;

    // Results of quizzes attempted by the user
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Result> results;

    public enum Role {
        ROLE_ADMIN,
        ROLE_USER
    }

}
