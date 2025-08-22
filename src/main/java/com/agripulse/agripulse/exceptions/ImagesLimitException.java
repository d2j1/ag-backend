package com.agripulse.agripulse.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ImagesLimitException extends Exception{
    public ImagesLimitException(String message){
        super(message);
    }
}
