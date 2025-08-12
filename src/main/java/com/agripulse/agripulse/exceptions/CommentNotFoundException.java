package com.agripulse.agripulse.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentNotFoundException extends Exception{

    public CommentNotFoundException(String message){
        super(message);
    }
}
