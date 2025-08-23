package com.agripulse.agripulse.repositories;

import com.agripulse.agripulse.models.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ImageRepository extends JpaRepository<Image, UUID> {
//    List<Image> findByPostId(UUID postId);
}
