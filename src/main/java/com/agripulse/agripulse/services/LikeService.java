package com.agripulse.agripulse.services;

import com.agripulse.agripulse.dto.PostLikeDto;
import com.agripulse.agripulse.exceptions.PostLikeNotCreatedException;
import com.agripulse.agripulse.exceptions.PostLikeNotFoundException;
import com.agripulse.agripulse.exceptions.PostNotFoundException;

import java.util.UUID;

public interface LikeService   {
    long likeThePost(PostLikeDto like) throws PostLikeNotCreatedException;

    long removeALike(UUID postId, UUID userId) throws PostLikeNotFoundException, PostNotFoundException;

    boolean hasLikedPostAlready(UUID uesrId, UUID postId);
}
