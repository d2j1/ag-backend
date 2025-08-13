package com.agripulse.agripulse.mapper;

import com.agripulse.agripulse.dto.CommentDto;
import com.agripulse.agripulse.dto.CommentResponseDto;
import com.agripulse.agripulse.models.Comment;


public class CommentMapper {

    public static  Comment toEntity(CommentDto commentDto){

        Comment comment = new Comment();

        comment.setContent(commentDto.getContent());
        comment.setUserId(commentDto.getUserId());
        comment.setUserName(commentDto.getUserName());
        comment.setProfileImageUrl(commentDto.getProfileImageUrl());
        comment.setPostId(commentDto.getPostId());
        return comment;
    }

    public static CommentResponseDto toResponseDto(Comment comment){

        CommentResponseDto commentResponseDto = new CommentResponseDto();

        commentResponseDto.setContent(comment.getContent());
        commentResponseDto.setUserId(comment.getUserId());
        commentResponseDto.setUserName(comment.getUserName());
        commentResponseDto.setProfileImageUrl(comment.getProfileImageUrl());
        commentResponseDto.setPostId(comment.getPostId());
        commentResponseDto.setCreatedAt(comment.getCreatedAt());
        commentResponseDto.setId(comment.getId());

        return commentResponseDto;
    }
}
