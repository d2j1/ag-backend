package com.agripulse.agripulse.controllers;

import com.agripulse.agripulse.DTO.PaginatedResponse;
import com.agripulse.agripulse.DTO.PostDto;
import com.agripulse.agripulse.DTO.PostResponseDto;
import com.agripulse.agripulse.exceptions.NoPostsFoundException;
import com.agripulse.agripulse.exceptions.PostNotCreatedException;
import com.agripulse.agripulse.exceptions.PostNotFoundException;
import com.agripulse.agripulse.mapper.PostMapper;
import com.agripulse.agripulse.models.Post;
import com.agripulse.agripulse.services.PostServiceImpl;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostServiceImpl postServiceImpl;

    public PostController(PostServiceImpl postServiceImpl){
        this.postServiceImpl = postServiceImpl;
    }


    @PostMapping
    public ResponseEntity<PostResponseDto> createPost(@RequestBody PostDto postDto) throws PostNotCreatedException {
        Post post = PostMapper.toEntity(postDto);
        PostResponseDto responseDto = PostMapper.toResponseDto(post);
        return new ResponseEntity<>( responseDto, HttpStatus.CREATED);
    }

    @GetMapping("/feed")
    public ResponseEntity<PaginatedResponse<PostResponseDto>> getAllPosts(@RequestParam int page, @RequestParam int size) throws NoPostsFoundException {
        PaginatedResponse<PostResponseDto> posts = postServiceImpl.getAllPosts(page, size);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostResponseDto> getPostById(@PathVariable UUID postId) throws PostNotFoundException {
        Post post = postServiceImpl.getPostById(postId);
        return new ResponseEntity<>(PostMapper.toResponseDto(post), HttpStatus.OK);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable("postId") UUID postId) throws PostNotFoundException {
        postServiceImpl.deletePost(postId);
        return new ResponseEntity<>("Post with id "+postId+" deleted", HttpStatus.OK);
    }

    @PutMapping("/{postId}")
    public ResponseEntity<PostResponseDto> updatePost(@RequestBody PostDto postDto, @PathVariable("postId") UUID postId) throws PostNotCreatedException, PostNotFoundException {
        Post post = PostMapper.toEntity(postDto);
         post = postServiceImpl.updatePost(postId, post);
         return new ResponseEntity<>( PostMapper.toResponseDto(post), HttpStatus.ACCEPTED );
    }
}
