package com.agripulse.agripulse.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Comment {

    @Id
    @GeneratedValue
    private Long id;

    private Long postId;

    private String userName;
    private Long userId;
    private String profileImageUrl;

    @Column(length = 500)
    private String content;

    private LocalDateTime commentedAt;
}
