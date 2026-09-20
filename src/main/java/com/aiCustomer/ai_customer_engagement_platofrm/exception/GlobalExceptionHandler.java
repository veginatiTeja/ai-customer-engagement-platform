package com.aiCustomer.ai_customer_engagement_platofrm.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleCustomerNotFound(CustomerNotFoundException exception) {
      Map<String, String> response = new HashMap<>();
      response.put("error", exception.getMessage());
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class) //   this handles validation failures caused by @Valid
    public ResponseEntity<Map<String, String>> handleValidation(MethodArgumentNotValidException exception) {
        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    @ExceptionHandler(ConversationNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleConversationNotFound(ConversationNotFoundException exception) {
        Map<String, String> response = new HashMap<>();
        response.put("error", exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(InvalidPaginationException.class)
    public ResponseEntity<Map<String, String>> handleInvalidPagination(InvalidPaginationException exception) {
        Map<String, String> response = new HashMap<>();
        response.put("error", exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
