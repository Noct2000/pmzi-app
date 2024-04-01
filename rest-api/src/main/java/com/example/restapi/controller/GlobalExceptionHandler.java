package com.example.restapi.controller;

import com.example.restapi.dto.BaseResponseDto;
import com.example.restapi.exception.AuthenticationException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.time.LocalDateTime;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<BaseResponseDto> handleValidationException(MethodArgumentNotValidException exception) {
        List<String> messages = exception.getBindingResult().getFieldErrors()
                .stream()
                .map(fieldError -> fieldError.getField() + " - " + fieldError.getDefaultMessage())
                .toList();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new BaseResponseDto(messages, LocalDateTime.now()));
    }

    @ExceptionHandler(value = AuthenticationException.class)
    public ResponseEntity<BaseResponseDto> handleAuthenticationException(AuthenticationException exception) {
        return getResponseForOneMessage(exception, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = EntityNotFoundException.class)
    public ResponseEntity<BaseResponseDto> handleEntityNotFoundException(EntityNotFoundException exception) {
        return getResponseForOneMessage(exception, HttpStatus.NOT_FOUND);
    }

    private ResponseEntity<BaseResponseDto> getResponseForOneMessage(Exception exception, HttpStatus status) {
        return ResponseEntity.status(status)
                .body(new BaseResponseDto(List.of(exception.getMessage()), LocalDateTime.now()));
    }
}
