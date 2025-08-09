package com.agripulse.agripulse.services;

import com.agripulse.agripulse.DTO.PaginatedResponse;
import com.agripulse.agripulse.DTO.PostResponseDto;
import com.agripulse.agripulse.exceptions.PostNotFoundException;
import com.agripulse.agripulse.mapper.PostMapper;
import com.agripulse.agripulse.models.Post;
import com.agripulse.agripulse.repositories.PostRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.UUID;
import java.util.stream.Collectors;

public class PostServiceImpl implements PostService{

    private final PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository){
        this.postRepository = postRepository;
    }

    @Override
    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    @Override
    public PaginatedResponse<PostResponseDto> getAllPosts(int pageNumber, int pageSize) {
        Page<Post> postPage = postRepository.findAll(PageRequest.of(pageNumber, pageSize));

        return new PaginatedResponse<>(
          postPage.getContent().stream()
                  .map(PostMapper::toDto)
                  .collect(Collectors.toList()),
                postPage.getNumber(),
                postPage.getTotalPages(),
                postPage.getTotalElements()
        );
    }

    @Override
    public Post getPostById(UUID postId) throws PostNotFoundException {
        return null;
    }

    @Override
    public Post updatePost(UUID postId) throws PostNotFoundException {
        return null;
    }

    @Override
    public void deletePost(UUID postId) throws PostNotFoundException {

    }
}
