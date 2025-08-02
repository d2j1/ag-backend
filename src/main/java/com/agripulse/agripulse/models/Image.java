package com.agripulse.agripulse.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Image {

    @Id
    @GeneratedValue
    private Long id;

    // s3 bucket url
    private String url;

    @ManyToOne
    private Post post;
}
