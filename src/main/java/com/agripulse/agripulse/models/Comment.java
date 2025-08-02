package com.agripulse.agripulse.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Comment {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Post post;

    @ManyToOne
    private User user;

    @Column(length = 500)
    private String content;

    private LocalDateTime commentedAt;
}
