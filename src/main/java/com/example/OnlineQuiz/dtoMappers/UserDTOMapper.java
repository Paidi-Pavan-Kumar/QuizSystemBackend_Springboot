package com.example.OnlineQuiz.dtoMappers;

import com.example.OnlineQuiz.dto.UserRequestDTO;
import com.example.OnlineQuiz.dto.UserResponseDTO;
import com.example.OnlineQuiz.entity.User;

public class UserDTOMapper {

    public static User toEntity(UserRequestDTO dto) {
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        if (dto.getRole() != null) {
            user.setRole(User.Role.valueOf(dto.getRole()));
        }
        return user;
    }

    public static UserResponseDTO toDTO(User user) {
        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setRole(user.getRole().name());
        return dto;
    }
    
}
