package com.agripulse.agripulse.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Like {

    @Id
    @GeneratedValue
    private Long id;

    private Long userId;
    private Long postId;

    private LocalDateTime likedAt;
}
