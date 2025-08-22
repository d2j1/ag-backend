package com.agripulse.agripulse.exceptions;

import com.agripulse.agripulse.services.ImageService;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ImageNotSavedException extends Exception{
    public ImageNotSavedException(String message){
        super(message);
    }
}
