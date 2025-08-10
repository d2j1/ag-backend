package com.agripulse.agripulse.services;

import com.agripulse.agripulse.DTO.PaginatedResponse;
import com.agripulse.agripulse.DTO.PostDto;
import com.agripulse.agripulse.DTO.PostResponseDto;
import com.agripulse.agripulse.exceptions.NoPostsFoundException;
import com.agripulse.agripulse.exceptions.PostNotCreatedException;
import com.agripulse.agripulse.exceptions.PostNotFoundException;
import com.agripulse.agripulse.models.Post;
import com.agripulse.agripulse.repositories.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


public interface PostService {

     Post createPost(Post post) throws PostNotCreatedException;
     PaginatedResponse<PostResponseDto> getAllPosts(int pageNumber, int pageSize) throws NoPostsFoundException;
     Post getPostById(UUID postId) throws PostNotFoundException;
     Post updatePost(UUID postId, Post post) throws PostNotFoundException, PostNotCreatedException;
     void deletePost(UUID postId) throws PostNotFoundException;

}
