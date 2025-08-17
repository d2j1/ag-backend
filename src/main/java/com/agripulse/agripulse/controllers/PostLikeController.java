package com.agripulse.agripulse.controllers;

import com.agripulse.agripulse.dto.PostLikeDto;
import com.agripulse.agripulse.dto.PostLikeResponseDto;

import com.agripulse.agripulse.exceptions.PostLikeNotCreatedException;
import com.agripulse.agripulse.exceptions.PostLikeNotFoundException;
import com.agripulse.agripulse.exceptions.PostNotFoundException;
import com.agripulse.agripulse.mapper.PostLikeMapper;
import com.agripulse.agripulse.services.LikeServiceImpl;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/likes")
public class PostLikeController {

    private LikeServiceImpl likeServiceImpl;

    public PostLikeController(LikeServiceImpl likeServiceImpl){
        this.likeServiceImpl= likeServiceImpl;
    }

    @PostMapping
    public ResponseEntity<PostLikeResponseDto> addLike(@RequestBody PostLikeDto postLikeDto) throws PostLikeNotCreatedException {
            long updatedLikeCount = likeServiceImpl.likeThePost(postLikeDto);
        return new ResponseEntity<>(PostLikeMapper.toResponseEntity(postLikeDto.getPostId(), updatedLikeCount, "Liked the Post"), HttpStatus.CREATED);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<PostLikeResponseDto> removeLike(@PathVariable UUID postId, @RequestBody PostLikeDto dto) throws PostLikeNotFoundException, PostNotFoundException {
        long updatedLikeCount = likeServiceImpl.removeALike(postId, dto.getUserId());
        return new ResponseEntity<>(PostLikeMapper.toResponseEntity(postId, updatedLikeCount, "Unliked the post"), HttpStatus.ACCEPTED);
    }

    @GetMapping("/has-liked")
    public ResponseEntity<Boolean> hasUserLikedThisPostAlready(@RequestParam("userId") UUID userId, @RequestParam("postId") UUID postId){

        boolean isExists = likeServiceImpl.hasLikedPostAlready(userId, postId);
        return new ResponseEntity<>(   isExists, HttpStatus.OK);
    }




}
