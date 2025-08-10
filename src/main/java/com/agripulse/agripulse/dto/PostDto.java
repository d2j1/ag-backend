package com.agripulse.agripulse.dto;

import com.agripulse.agripulse.models.CTAType;
import com.agripulse.agripulse.models.PostType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {

    private UUID id;

    private String userName;
    private UUID userId;
    private String profileImageUrl;

    private PostType type;

    private CTAType ctaType;

    private String content;

    // will store image urls in this when fetched from the database from image table
    // upload all these images from frontend to s3 and get the urls and send those urls here
    private List<String> imageUrls;
}
