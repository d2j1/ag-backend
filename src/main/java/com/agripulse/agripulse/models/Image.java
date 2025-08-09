package com.agripulse.agripulse.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Image {

    @Id
    @GeneratedValue
    private UUID id;

    private UUID post_id;

    // s3 bucket url
    private String imageUrl;

    private short imageIndex;

    private int imageWidth;
    private int imageHeight;

    private int imageSize;

    private LocalDateTime uploadedAt;

    @PrePersist
    public void onCreate() {
        LocalDateTime now = LocalDateTime.now();
        this.uploadedAt = now;

    }

}
