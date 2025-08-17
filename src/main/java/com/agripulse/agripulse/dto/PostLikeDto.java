package com.agripulse.agripulse.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class PostLikeDto {

    private UUID userId;
    private UUID postId;
}
