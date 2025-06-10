package com.example.OnlineQuiz.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "options")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Option {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String text;

    @Column(nullable = false)
    private boolean isCorrect;

    // Each option belongs to one question
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "question_id")
    // @Lazy
    private Question question;
}
