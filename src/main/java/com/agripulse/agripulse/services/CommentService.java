package com.agripulse.agripulse.services;

import com.agripulse.agripulse.dto.CommentResponseDto;
import com.agripulse.agripulse.dto.PaginatedResponse;
import com.agripulse.agripulse.exceptions.CommentNotCreatedException;
import com.agripulse.agripulse.exceptions.CommentNotFoundException;
import com.agripulse.agripulse.exceptions.NoCommentsFoundException;
import com.agripulse.agripulse.exceptions.PostNotFoundException;
import com.agripulse.agripulse.models.Comment;

import java.util.List;
import java.util.UUID;

public interface CommentService {

    Comment createComment(Comment comment) throws CommentNotCreatedException, PostNotFoundException;
    // paginated
    PaginatedResponse<CommentResponseDto> getCommentsByPostId(UUID postId, int pageNumber, int size) throws NoCommentsFoundException;
    void deleteComment(UUID commentId) throws CommentNotFoundException;
    PaginatedResponse<CommentResponseDto> getCommentByUserId(UUID userId, int pageNumber, int size) throws NoCommentsFoundException;
}
