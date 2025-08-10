package com.agripulse.agripulse.DTO;

import com.agripulse.agripulse.models.CTAType;
import com.agripulse.agripulse.models.PostType;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class PostResponseDto {

    private UUID id;

    private String userName;
    private UUID userId;
    private String profileImageUrl;

    private PostType type;

    private CTAType ctaType;

    private String content;

    private Long likeCount;
    private Long shareCount;
    // will store image urls in this when fetched from the database from image table
    // upload all these images from frontend to s3 and get the urls and send those urls here
    private List<String> imageUrls;

}
