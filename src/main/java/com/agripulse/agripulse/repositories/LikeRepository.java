package com.agripulse.agripulse.repositories;

import com.agripulse.agripulse.models.PostLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Repository
public interface LikeRepository extends JpaRepository<PostLike, UUID> {

        boolean existsByUserIdAndPostId(UUID userId, UUID postId);

        @Modifying
        @Transactional
        void deleteByUserIdAndPostId(UUID userId, UUID postId);
}
