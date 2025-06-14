package com.example.OnlineQuiz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.OnlineQuiz.dto.UserRequestDTO;
import com.example.OnlineQuiz.dto.UserResponseDTO;
import com.example.OnlineQuiz.service.UserService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;


@CrossOrigin(origins = "http://localhost:5173") //a way to enable cors
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;
    
    @PostMapping("/login")
    public String login(@RequestBody UserRequestDTO user) {
        return userService.verify(user);
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> createUser(@Valid @RequestBody UserRequestDTO dto) {
        UserResponseDTO response = userService.createUser(dto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('ADMIN')")
    @GetMapping("/profile")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<UserResponseDTO> profilePage(@AuthenticationPrincipal UserDetails userDetails) {
        UserResponseDTO userResponse = userService.getUserProfile(userDetails.getUsername());
        return ResponseEntity.ok(userResponse);
    }

    @GetMapping("/sde")
    public String sde() {
        return "sde";
    }
}
