package com.agripulse.agripulse.services;

import com.agripulse.agripulse.DTO.PaginatedResponse;
import com.agripulse.agripulse.DTO.PostDto;
import com.agripulse.agripulse.DTO.PostResponseDto;
import com.agripulse.agripulse.exceptions.PostNotFoundException;
import com.agripulse.agripulse.models.Post;
import com.agripulse.agripulse.repositories.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


public interface PostService {

    public Post createPost(Post post);
    public PaginatedResponse<PostResponseDto> getAllPosts(int pageNumber, int pageSize);
    public Post getPostById(UUID postId) throws PostNotFoundException;
    public Post updatePost(UUID postId) throws PostNotFoundException;
    public void deletePost(UUID postId) throws PostNotFoundException;

}
