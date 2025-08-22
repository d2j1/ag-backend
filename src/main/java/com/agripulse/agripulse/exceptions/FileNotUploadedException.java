package com.agripulse.agripulse.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FileNotUploadedException extends Exception{
    public FileNotUploadedException(String message){
        super(message);
    }
}
