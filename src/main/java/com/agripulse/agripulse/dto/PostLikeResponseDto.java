package com.agripulse.agripulse.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class PostLikeResponseDto {

    private UUID postId;
    private Long likeCount;
    private String message;
}
