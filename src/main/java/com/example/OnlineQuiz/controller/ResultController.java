package com.example.OnlineQuiz.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.OnlineQuiz.dto.ResultRequestDTO;
import com.example.OnlineQuiz.dto.ResultResponseDTO;
import com.example.OnlineQuiz.service.ResultService;

@RestController
@RequestMapping("/api/results")
public class ResultController {

    @Autowired
    private ResultService resultService;

    // POST - Submit a quiz result
    @PostMapping("/submit/{userId}")
    public ResponseEntity<ResultResponseDTO> submitResult(
            @PathVariable Long userId,
            @RequestBody @Validated ResultRequestDTO dto
    ) {
        return ResponseEntity.ok(resultService.saveResult(dto, userId));
    }

    // GET - All results for a user
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ResultResponseDTO>> getResultsByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(resultService.getResultsByUserId(userId));
    }

    // GET - All results (admin only)
    @GetMapping("/admin/all")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<ResultResponseDTO>> getAllResults() {
        return ResponseEntity.ok(resultService.getAllResults());
    }
}