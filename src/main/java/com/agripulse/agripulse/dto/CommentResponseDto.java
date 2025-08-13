package com.agripulse.agripulse.dto;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class CommentResponseDto {

    private UUID id;
    private UUID postId;

    private String userName;
    private UUID userId;
    private String profileImageUrl;
    private String content;
    private LocalDateTime createdAt;
}
