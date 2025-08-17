package com.agripulse.agripulse.repositories;

import com.agripulse.agripulse.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PostRepository extends JpaRepository<Post, UUID> {

        @Query("SELECT p.likeCount FROM Post p WHERE p.id = :postId")
        Long findLikeCountById(UUID postId);

        @Modifying
        @Query("UPDATE Post p SET p.likeCount = COALESCE(p.likeCount, 0) + 1 WHERE p.id = :postId")
        int incrementLikeCount(@Param("postId") UUID postId);

        @Modifying
        @Query("UPDATE Post p SET p.likeCount = CASE WHEN p.likeCount <= 0 THEN 0 ELSE p.likeCount - 1 END WHERE p.id = :postId")
        int decrementLikeCount(@Param("postId") UUID postId);

}
