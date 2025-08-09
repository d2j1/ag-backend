package com.agripulse.agripulse.exceptions;


import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class PostNotFoundException extends Exception{

    private UUID postId;

    public PostNotFoundException(UUID postId, String message){
        super(message);
        this.postId = postId;
    }
}
