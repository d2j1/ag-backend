package com.agripulse.agripulse.exceptionhandler;

import com.agripulse.agripulse.exceptions.NoPostsFoundException;
import com.agripulse.agripulse.exceptions.PostNotCreatedException;
import com.agripulse.agripulse.exceptions.PostNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ExceptionHandlers {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionDto> handleGenericException(Exception exception){
        return new ResponseEntity<>(new ExceptionDto(exception.getMessage(), "Internal Server Error"), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NoPostsFoundException.class)
    public ResponseEntity<String> handleNoPostsFoundException(NoPostsFoundException exception){
        return new ResponseEntity<>( exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PostNotCreatedException.class)
    public ResponseEntity<String> handlePostNotCreatedException(PostNotCreatedException exception){
        return new ResponseEntity<>( exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PostNotFoundException.class)
    public ResponseEntity<String> handlePostNotFoundException( PostNotFoundException exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }
}
