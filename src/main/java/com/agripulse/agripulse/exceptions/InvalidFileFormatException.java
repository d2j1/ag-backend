package com.agripulse.agripulse.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InvalidFileFormatException extends Exception{
    public InvalidFileFormatException(String message){
        super(message);
    }
}
