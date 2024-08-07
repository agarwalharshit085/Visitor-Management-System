package com.example.VisitorManagementSystem.util;

import com.example.VisitorManagementSystem.exceptions.BadRequestException;
import com.example.VisitorManagementSystem.exceptions.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(annotations = RestController.class)
public class RestExceptionalHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<String> handleBadRequestException(final BadRequestException exception){
        return ResponseEntity.badRequest().body(exception.getMessage());
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handleNotFoundException(final NotFoundException exception){
        return ResponseEntity.notFound().build();
    }



    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(final RuntimeException exception){
        return ResponseEntity.internalServerError().body(exception.getMessage());
    }


}