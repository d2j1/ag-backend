package com.agripulse.agripulse.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NoPostsFoundException extends Exception {

    public NoPostsFoundException(String message){
        super(message);
    }
}
