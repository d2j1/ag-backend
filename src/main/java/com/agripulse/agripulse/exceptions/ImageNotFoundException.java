package com.agripulse.agripulse.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ImageNotFoundException extends Exception{
    public ImageNotFoundException(String message){
        super(message);
    }
}
