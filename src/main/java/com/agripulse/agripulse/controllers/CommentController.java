package com.agripulse.agripulse.controllers;


import com.agripulse.agripulse.dto.CommentDto;
import com.agripulse.agripulse.dto.CommentResponseDto;
import com.agripulse.agripulse.exceptions.CommentNotCreatedException;
import com.agripulse.agripulse.mapper.CommentMapper;
import com.agripulse.agripulse.models.Comment;
import com.agripulse.agripulse.services.CommentServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comments")
public class CommentController {

    private final CommentServiceImpl commentServiceImpl;

    public CommentController(CommentServiceImpl commentServiceImpl){
        this.commentServiceImpl = commentServiceImpl;
    }

    @PostMapping
    public ResponseEntity<CommentResponseDto> createComment(@RequestBody CommentDto commentDto) throws CommentNotCreatedException {
        Comment comment = commentServiceImpl.createComment(CommentMapper.toEntity(commentDto));
        return new ResponseEntity<>(CommentMapper.toResponseDto(comment), HttpStatus.CREATED);
    }

}
