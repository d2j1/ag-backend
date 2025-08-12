package com.agripulse.agripulse.services;

import com.agripulse.agripulse.exceptions.CommentNotCreatedException;
import com.agripulse.agripulse.exceptions.CommentNotFoundException;
import com.agripulse.agripulse.models.Comment;

import java.util.List;
import java.util.UUID;

public interface CommentService {

    Comment createComment(Comment comment) throws CommentNotCreatedException;
    // paginated
    List<Comment> getCommentsByPostId(UUID postId, int pageNumber, int size);
    void deleteComment(UUID commentId) throws CommentNotFoundException;
    List<Comment> getCommentByUserId(UUID userId, int pageNumber, int size);
}
