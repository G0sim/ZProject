package com.example.zproject.repository;

import com.example.zproject.model.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Integer> {
     public Image findByPosts_PostId(Integer postId);
}
