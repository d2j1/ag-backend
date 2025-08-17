package com.agripulse.agripulse.services;

import com.agripulse.agripulse.dto.PostLikeDto;
import com.agripulse.agripulse.exceptions.PostLikeNotCreatedException;
import com.agripulse.agripulse.exceptions.PostLikeNotFoundException;
import com.agripulse.agripulse.exceptions.PostNotFoundException;
import com.agripulse.agripulse.mapper.PostLikeMapper;

import com.agripulse.agripulse.repositories.LikeRepository;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class LikeServiceImpl implements  LikeService{

    private LikeRepository likeRepository;
    private PostServiceImpl postServceImpl;

    public LikeServiceImpl(LikeRepository likeRepository, PostServiceImpl postServceImpl){
        this.likeRepository = likeRepository;
        this.postServceImpl = postServceImpl;
    }

    @Override
    public long likeThePost(PostLikeDto likeDto) throws PostLikeNotCreatedException {
        try{
        likeRepository.save(PostLikeMapper.toEntity(likeDto));
        return postServceImpl.incrementLikeCount(likeDto.getPostId());

        }catch (Exception ex){
            throw new PostLikeNotCreatedException("Could not add the like");
        }
    }

    @Override
    public long removeALike(UUID postId, UUID userId) throws PostLikeNotFoundException, PostNotFoundException {

        try{
        likeRepository.deleteByUserIdAndPostId(userId, postId);
        return postServceImpl.decrementLikeCount(postId);
        }catch (EmptyResultDataAccessException exception){
            throw new PostLikeNotFoundException("You have not liked the post before.");
        }catch (Exception ex){
            ex.printStackTrace();
            throw new PostLikeNotFoundException("You have not liked the post before.");
        }
    }

    @Override
    public boolean hasLikedPostAlready(UUID uesrId, UUID postId) {

        boolean ans = likeRepository.existsByUserIdAndPostId(uesrId, postId);
        System.out.println(ans);
            return ans;
    }
}
