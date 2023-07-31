//package com.example.exceptions;
//
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
//
//@ControllerAdvice
//public class GlobalExceptionHandler {
//
//    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
//    public ResponseEntity<?> handleTypeMismatch(MethodArgumentTypeMismatchException e) {
//        return ResponseEntity.badRequest().body("Invalid ID format. Please provide a valid ID.");
//    }
//}
