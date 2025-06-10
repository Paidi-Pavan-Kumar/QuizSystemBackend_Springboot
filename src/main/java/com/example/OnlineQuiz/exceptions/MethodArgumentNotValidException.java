package com.example.OnlineQuiz.exceptions;

public class MethodArgumentNotValidException extends RuntimeException {
    public MethodArgumentNotValidException(String message) {
        super(message);
    }

    public Object getBindingResult() {
        throw new UnsupportedOperationException("Unimplemented method 'getBindingResult'");
    }
}

