package com.agripulse.agripulse.services;

import com.agripulse.agripulse.dto.CommentResponseDto;
import com.agripulse.agripulse.dto.PaginatedResponse;
import com.agripulse.agripulse.exceptions.*;
import com.agripulse.agripulse.mapper.CommentMapper;
import com.agripulse.agripulse.models.Comment;
import com.agripulse.agripulse.repositories.CommentRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CommentServiceImpl implements CommentService{

    private final CommentRepository commentRepository;
    private final PostServiceImpl postServiceImpl;

    public CommentServiceImpl(CommentRepository commentRepository, PostServiceImpl postServiceImpl){
        this.commentRepository = commentRepository;
        this.postServiceImpl = postServiceImpl;
    }

    @Override
    public Comment createComment(Comment comment) throws CommentNotCreatedException, PostNotFoundException {

        try{
            postServiceImpl.validatePostExists(comment.getPostId());
        return commentRepository.save(comment);
        }catch (DataIntegrityViolationException e){
            // invalid data in post like nulls
            throw new CommentNotCreatedException("Could not create the comment due to invalid data. Please try again!");
        }catch (DataAccessException e){
            // not able to access the database
            throw new CommentNotCreatedException("Could not create the comment due to system unavailability. Please try after some time!");
        }
    }

    @Override
    public PaginatedResponse<CommentResponseDto> getCommentsByPostId(UUID postId, int pageNumber, int pageSize) throws NoCommentsFoundException {

        Page<Comment> commentPage = commentRepository.findByPostId(postId, PageRequest.of(pageNumber, pageSize));

        if(commentPage.isEmpty()){
            throw new NoCommentsFoundException("No Comments Exception!");
        }

        List<CommentResponseDto> commentResponseDtos = commentPage.getContent()
                .stream()
                .map(CommentMapper::toResponseDto)
                .toList();

        return new PaginatedResponse<CommentResponseDto>(commentResponseDtos,
                commentPage.getNumber(),
                commentPage.getTotalPages(),
                commentPage.getTotalElements());

    }

    @Override
    public void deleteComment(UUID commentId) throws CommentNotFoundException {
        if( !commentRepository.existsById(commentId)){
            throw new CommentNotFoundException("Comment not found with id:");
        }
        commentRepository.deleteById(commentId);
    }

    @Override
    public PaginatedResponse<CommentResponseDto> getCommentByUserId(UUID userId, int pageNumber, int size) throws NoCommentsFoundException {

        Page<Comment> commentPage = commentRepository.findByUserId(userId, PageRequest.of(pageNumber, size));

        if(commentPage.isEmpty()){
            throw  new NoCommentsFoundException("No Comments found");
        }

        List<CommentResponseDto> commentResponseDtos = commentPage.getContent()
                .stream()
                .map(CommentMapper::toResponseDto)
                .toList();

        return new PaginatedResponse<CommentResponseDto>( commentResponseDtos,
                commentPage.getNumber(),
                commentPage.getTotalPages(),
                commentPage.getTotalElements()
                );
    }
}
