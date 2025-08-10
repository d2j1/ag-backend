package com.agripulse.agripulse.mapper;

import com.agripulse.agripulse.DTO.PostDto;
import com.agripulse.agripulse.DTO.PostResponseDto;
import com.agripulse.agripulse.models.Post;

public class PostMapper {

    public static Post toEntity(PostDto dto){
        Post post = new Post();

        post.setId(dto.getId());
        post.setContent(dto.getContent());
        post.setType(dto.getType());
        post.setCtaType(dto.getCtaType());
        post.setUserId(dto.getUserId());
        post.setProfileImageUrl(dto.getProfileImageUrl());
        post.setUserName(dto.getUserName());

        return post;
    }

    public static PostResponseDto toResponseDto(Post post){
        PostResponseDto postDto = new PostResponseDto();

        postDto.setContent(post.getContent());
        postDto.setType(post.getType());
        postDto.setCtaType(post.getCtaType());
        postDto.setId(post.getId());
        postDto.setUserId(post.getUserId());
        postDto.setUserName(post.getUserName());
        postDto.setProfileImageUrl(post.getProfileImageUrl());
        postDto.setLikeCount(post.getLikeCount());
        postDto.setShareCount(post.getShareCount());

        return postDto;
    }
}
