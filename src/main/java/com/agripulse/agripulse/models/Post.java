package com.agripulse.agripulse.models;

import jakarta.persistence.*;
import org.hibernate.engine.internal.Cascade;

import java.util.List;
import java.time.LocalDateTime;

@Entity
public class Post {

    @Id
    @GeneratedValue
    private Long id;

    private String userName;
    private Long userId;
    private String profileImageUrl;

    @Enumerated(EnumType.STRING)
    private PostType type;

    @Enumerated(EnumType.STRING)
    private CTAType ctaType;

    @Column(length = 1000)
    private String content;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;



    private Long likeCount;

    private int shareCount;
}
