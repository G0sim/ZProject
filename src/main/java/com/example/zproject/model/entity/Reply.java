package com.example.zproject.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

//댓글 엔티티
@Entity
@Data
public class Reply {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer replyId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "postId")
    private Post postId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User replyWriter;
    private String replyContent;
    private LocalDateTime replyDate;

    @OneToMany(mappedBy = "repliesId", fetch = FetchType.LAZY)
    private List<ReReply> reReplies;
}
