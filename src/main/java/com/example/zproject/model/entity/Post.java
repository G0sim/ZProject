package com.example.zproject.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

//게시글 엔티티
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer postId;
    private String cate;
    private String title;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User postWriter;
    private String postContent;
    private LocalDateTime postDate;
    private Integer views;

    @OneToMany(mappedBy = "postId", fetch = FetchType.LAZY)
    private List<Reply> replies;
    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY)
    private List<Like> likes;
    @OneToMany(mappedBy = "posts", fetch = FetchType.LAZY)
    private List<Image> images;

}
