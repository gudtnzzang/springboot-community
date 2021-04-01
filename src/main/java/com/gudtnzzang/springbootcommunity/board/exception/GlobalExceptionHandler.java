package com.gudtnzzang.springbootcommunity.board.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;


@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponse> handleUserAuthException(AccessDeniedException e) {
        return new ResponseEntity<>(new ErrorResponse(e.getMessage(), new Date()), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(Exception e) {
        return new ResponseEntity<>(new ErrorResponse(e.getMessage(), new Date()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
