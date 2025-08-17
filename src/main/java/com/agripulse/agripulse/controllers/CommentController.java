package com.agripulse.agripulse.controllers;


import com.agripulse.agripulse.dto.CommentDto;
import com.agripulse.agripulse.dto.CommentResponseDto;
import com.agripulse.agripulse.dto.PaginatedResponse;
import com.agripulse.agripulse.exceptions.CommentNotCreatedException;
import com.agripulse.agripulse.exceptions.CommentNotFoundException;
import com.agripulse.agripulse.exceptions.NoCommentsFoundException;
import com.agripulse.agripulse.exceptions.PostNotFoundException;
import com.agripulse.agripulse.mapper.CommentMapper;
import com.agripulse.agripulse.models.Comment;
import com.agripulse.agripulse.services.CommentServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/comments")
public class CommentController {

    private final CommentServiceImpl commentServiceImpl;

    public CommentController(CommentServiceImpl commentServiceImpl){
        this.commentServiceImpl = commentServiceImpl;
    }

    @PostMapping
    public ResponseEntity<CommentResponseDto> createComment(@RequestBody CommentDto commentDto) throws CommentNotCreatedException, PostNotFoundException {
        Comment comment = commentServiceImpl.createComment(CommentMapper.toEntity(commentDto));
        return new ResponseEntity<>(CommentMapper.toResponseDto(comment), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<PaginatedResponse<CommentResponseDto>> getAllCommentOfPost(@RequestParam("postId") UUID postId, @RequestParam("page") int page, @RequestParam("size") int size) throws NoCommentsFoundException {
        PaginatedResponse<CommentResponseDto> comments = commentServiceImpl.getCommentsByPostId(postId, page, size);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<String> deleteComment( @PathVariable("commentId") UUID commentId) throws CommentNotFoundException {
        commentServiceImpl.deleteComment(commentId);
        return new ResponseEntity<>("Comment deleted", HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<PaginatedResponse<CommentResponseDto>> getAllCommentByUserId(@PathVariable("userId") UUID userId, @RequestParam("page") int page, @RequestParam("size") int size) throws NoCommentsFoundException {
        PaginatedResponse<CommentResponseDto> comments = commentServiceImpl.getCommentByUserId(userId, page, size);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

}
