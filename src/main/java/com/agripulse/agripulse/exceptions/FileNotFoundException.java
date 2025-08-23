package com.agripulse.agripulse.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FileNotFoundException extends Exception{
    public FileNotFoundException(String message){
        super(message);
    }
}
