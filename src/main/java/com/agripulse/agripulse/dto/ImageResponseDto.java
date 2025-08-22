package com.agripulse.agripulse.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class ImageResponseDto {

    private UUID postId;
    private List<String> imageUrls ;

}
