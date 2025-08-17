package com.agripulse.agripulse.mapper;

import com.agripulse.agripulse.dto.PostLikeDto;
import com.agripulse.agripulse.dto.PostLikeResponseDto;
import com.agripulse.agripulse.models.PostLike;

import java.util.UUID;

public class PostLikeMapper {

    public static PostLike toEntity(PostLikeDto dto){
        PostLike like = new PostLike();
        like.setPostId(dto.getPostId());
        like.setUserId(dto.getUserId());

        return like;
    }

    public static PostLikeResponseDto toResponseEntity(UUID postId, Long likeCount, String message){
        PostLikeResponseDto responseDto = new PostLikeResponseDto();
        responseDto.setLikeCount(likeCount);
        responseDto.setMessage(message);
        responseDto.setPostId(postId);
        return responseDto;
    }
}
