package com.springboot.bloggingApp.exceptions;

import com.springboot.bloggingApp.payloads.ApiResponse;
import io.jsonwebtoken.JwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> ResourceNotFoundException(ResourceNotFoundException ex) {

        String message = ex.getMessage();

        ApiResponse apiResponse = new ApiResponse(message, true);

        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RuntimeConflictException.class)
    public ResponseEntity<ApiResponse> handleRuntimeConflictException(RuntimeConflictException exception){

        String message = exception.getMessage();

        ApiResponse apiResponse = new ApiResponse(message, true);

        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(JwtException.class)
    public ResponseEntity<ApiResponse> handleJwtException(JwtException ex) {

        ApiResponse apiResponse = new ApiResponse(ex.getMessage(),true);

        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.UNAUTHORIZED);
    }
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ApiResponse> handleAuthenticationException(AuthenticationException ex) {

        ApiResponse apiResponse = new ApiResponse(ex.getMessage(),true);

        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.UNAUTHORIZED);

    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        Map<String, String> resp = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error->{
         String fieldName = ((FieldError)error).getField();
         String message = error.getDefaultMessage();
         resp.put(fieldName,message);
        });
        return new ResponseEntity<Map<String,String>>(resp, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApiResponse> handleAccessDeniedException(AccessDeniedException ex) {
        ApiResponse apiResponse = new ApiResponse(ex.getMessage(), true);
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.FORBIDDEN);
    }
}