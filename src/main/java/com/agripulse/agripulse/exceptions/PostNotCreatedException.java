package com.agripulse.agripulse.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostNotCreatedException extends Exception {
    public PostNotCreatedException(String message){
        super(message);
    }
}
