package com.example.zproject.model.entity;

import jakarta.persistence.*;
import lombok.Data;

//게시글 이미지 엔티티
@Entity
@Data
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer imageId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "postId")
    private Post posts;
    private String imageUrl;
    private String type;

}
