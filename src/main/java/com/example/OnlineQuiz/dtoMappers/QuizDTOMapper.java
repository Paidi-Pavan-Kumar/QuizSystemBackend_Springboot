package com.example.OnlineQuiz.dtoMappers;

import java.util.stream.Collectors;

import com.example.OnlineQuiz.dto.AttemptOptionDTO;
import com.example.OnlineQuiz.dto.AttemptQuestionDTO;
import com.example.OnlineQuiz.dto.AttemptQuizResponseDTO;
import com.example.OnlineQuiz.dto.OptionResponseDTO;
import com.example.OnlineQuiz.dto.QuestionResponseDTO;
import com.example.OnlineQuiz.dto.QuizRequestDTO;
import com.example.OnlineQuiz.dto.QuizResponseDTO;
import com.example.OnlineQuiz.entity.Option;
import com.example.OnlineQuiz.entity.Question;
import com.example.OnlineQuiz.entity.Quiz;
import com.example.OnlineQuiz.entity.User;

public class QuizDTOMapper {
    
    public static Quiz toEntity(QuizRequestDTO dto, User user) {
        Quiz quiz = new Quiz();
        quiz.setTitle(dto.getTitle());
        quiz.setDescription(dto.getDescription());
        quiz.setCreatedAt(java.time.LocalDateTime.now());
        quiz.setCreatedBy(user);

        if(dto.getQuestions() != null) {
            quiz.setQuestions(dto.getQuestions().stream()
                .map(qDto -> {
                    Question question = new Question();
                    question.setContent(qDto.getContent());
                    question.setQuiz(quiz);

                    if(qDto.getOptions() != null) {
                        question.setOptions(qDto.getOptions().stream()
                            .map(oDto -> {
                                Option option = new Option();
                                option.setText(oDto.getText());
                                option.setCorrect(oDto.isCorrect());
                                option.setQuestion(question);
                                return option;
                            }).collect(Collectors.toList())
                        );
                    }
                    return question;
                }).collect(Collectors.toList())
            );
        }
        return quiz;
    }

    public static QuizResponseDTO toDTO(Quiz quiz) {
        QuizResponseDTO dto = new QuizResponseDTO();
        dto.setId(quiz.getId());
        dto.setTitle(quiz.getTitle());
        dto.setDescription(quiz.getDescription());
        dto.setCreatedAt(quiz.getCreatedAt());
        dto.setCreatedBy(quiz.getCreatedBy().getId());
        if(quiz.getQuestions() != null) {
            dto.setQuestions(quiz.getQuestions().stream()
                .map(q -> {
                    QuestionResponseDTO qDto = new QuestionResponseDTO();
                    qDto.setId(q.getId());
                    qDto.setContent(q.getContent());
                    
                    if(q.getOptions() != null) {
                        qDto.setOptions(q.getOptions().stream()
                            .map(o -> {
                                OptionResponseDTO oDto = new OptionResponseDTO();
                                oDto.setId(o.getId());
                                oDto.setText(o.getText());
                                oDto.setCorrect(o.isCorrect());
                                return oDto;
                            }).collect(Collectors.toList())
                        );
                    }
                    return qDto;
                }).collect(Collectors.toList())
            );
        }
        return dto;
    }

    public static AttemptQuizResponseDTO toAttemptDTO(Quiz quiz) {
        AttemptQuizResponseDTO dto = new AttemptQuizResponseDTO();
        dto.setQuizId(quiz.getId());
        dto.setTitle(quiz.getTitle());
        dto.setDescription(quiz.getDescription());
        if(quiz.getQuestions() != null) {
            dto.setQuestions(quiz.getQuestions().stream()
                .map(q -> {
                    AttemptQuestionDTO qDto = new AttemptQuestionDTO();
                    qDto.setQuestionId(q.getId());
                    qDto.setContent(q.getContent());
                    
                    if(q.getOptions() != null) {
                        qDto.setOptions(q.getOptions().stream()
                            .map(o -> {
                                AttemptOptionDTO oDto = new AttemptOptionDTO();
                                oDto.setOptionId(o.getId());
                                oDto.setText(o.getText());
                                return oDto;
                            }).collect(Collectors.toList())
                        );
                    }
                    return qDto;
                }).collect(Collectors.toList())
            );
        }
        return dto;
    }
}
