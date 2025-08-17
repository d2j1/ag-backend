package com.agripulse.agripulse.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostLikeNotFoundException extends  Exception{

    public PostLikeNotFoundException(String message){
        super(message);
    }
}
