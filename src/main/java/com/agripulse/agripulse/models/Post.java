package com.agripulse.agripulse.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue
    private UUID id;

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

    private Long shareCount;

    @PrePersist
    public void onCreate() {
        LocalDateTime now = LocalDateTime.now();
        this.createdAt = now;
        this.updatedAt = now;
    }

    @PreUpdate
    public void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
