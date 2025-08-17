package com.agripulse.agripulse.exceptions;

import com.agripulse.agripulse.models.PostLike;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostLikeNotCreatedException extends Exception{
    public PostLikeNotCreatedException(String message){
        super(message);
    }
}
