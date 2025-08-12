package com.agripulse.agripulse.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentNotCreatedException extends Exception{

    public CommentNotCreatedException(String message){
        super(message);
    }
}
