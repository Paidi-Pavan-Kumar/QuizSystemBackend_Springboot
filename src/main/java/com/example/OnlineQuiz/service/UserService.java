package com.example.OnlineQuiz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.OnlineQuiz.dto.UserRequestDTO;
import com.example.OnlineQuiz.dto.UserResponseDTO;
import com.example.OnlineQuiz.dtoMappers.UserDTOMapper;
import com.example.OnlineQuiz.entity.User;
import com.example.OnlineQuiz.exceptions.ResourceNotFoundException;
import com.example.OnlineQuiz.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserService {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JWTService jwtService;

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public String verify(UserRequestDTO user) {
        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
        );

        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(user.getUsername());
        }
        return "fail";
    }

    public UserResponseDTO createUser(UserRequestDTO dto) {
        // 1. Check for existing email and username
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }

        Optional<User> existingUser = userRepository.findByUsername(dto.getUsername());
        if (existingUser.isPresent()) {
            throw new IllegalArgumentException("Username already exists");
        }

        // 2. Convert DTO to Entity
        User user = UserDTOMapper.toEntity(dto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // 3. Save and return response
        User savedUser = userRepository.save(user);
        return UserDTOMapper.toDTO(savedUser);
    }

    public UserResponseDTO getUserById(Long id) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("User with ID " + id + " not found"));
        return UserDTOMapper.toDTO(user);
    }

    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll()
            .stream()
            .map(UserDTOMapper::toDTO)
            .collect(Collectors.toList());
    }

    public UserResponseDTO updateUser(Long id, UserRequestDTO dto) {
        User existingUser = userRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("User with ID " + id + " not found"));

        // Optional: check for email/username conflicts
        if (!existingUser.getEmail().equals(dto.getEmail()) && userRepository.existsByEmail(dto.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }

        if (!existingUser.getUsername().equals(dto.getUsername()) &&
            userRepository.findByUsername(dto.getUsername()).isPresent()) {
            throw new IllegalArgumentException("Username already exists");
        }

        // Update fields
        existingUser.setUsername(dto.getUsername());
        existingUser.setEmail(dto.getEmail());
        existingUser.setPassword(passwordEncoder.encode(dto.getPassword()));
        if (dto.getRole() != null) {
            existingUser.setRole(User.Role.valueOf(dto.getRole()));
        }

        User updatedUser = userRepository.save(existingUser);
        return UserDTOMapper.toDTO(updatedUser);
    }

    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("User with ID " + id + " not found");
        }
        userRepository.deleteById(id);
    }

    public UserResponseDTO getUserProfile(String username) {
        User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new ResourceNotFoundException("User not found with username: " + username));
    return UserDTOMapper.toDTO(user);
    }

}
