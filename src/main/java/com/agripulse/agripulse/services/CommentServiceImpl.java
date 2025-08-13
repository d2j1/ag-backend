package com.agripulse.agripulse.services;

import com.agripulse.agripulse.exceptions.CommentNotCreatedException;
import com.agripulse.agripulse.exceptions.CommentNotFoundException;
import com.agripulse.agripulse.models.Comment;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CommentServiceImpl implements CommentService{

    @Override
    public Comment createComment(Comment comment) throws CommentNotCreatedException {




        return null;
    }

    @Override
    public List<Comment> getCommentsByPostId(UUID postId, int pageNumber, int size) {
        return List.of();
    }

    @Override
    public void deleteComment(UUID commentId) throws CommentNotFoundException {

    }

    @Override
    public List<Comment> getCommentByUserId(UUID userId, int pageNumber, int size) {
        return List.of();
    }
}
