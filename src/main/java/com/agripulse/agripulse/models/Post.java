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

    @ManyToOne
    private User user;

    @Enumerated(EnumType.STRING)
    private PostType type;

    @Enumerated(EnumType.STRING)
    private CTAType ctaType;

    @Column(length = 1000)
    private String content;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @OneToMany( mappedBy = "post", cascade = CascadeType.ALL)
    private List<Image> images;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Like> likes;

    @OneToMany(mappedBy = "comment", cascade= CascadeType.ALL)
    private List<Comment> comments;

    private int shareCount;
}
