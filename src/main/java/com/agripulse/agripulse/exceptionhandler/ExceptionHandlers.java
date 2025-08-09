package com.agripulse.agripulse.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.DataTruncation;

@ControllerAdvice
public class ExceptionHandlers {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionDto> handleGenericException(Exception exception){
        return new ResponseEntity<>(new ExceptionDto(exception.getMessage(), "Internal Server Error"), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
