package com.agripulse.agripulse.exceptionhandler;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ExceptionDto {

    private String error;
    private String message;

    public ExceptionDto(String error, String message){
        this.error = error;
        this.message= message;
    }
}
