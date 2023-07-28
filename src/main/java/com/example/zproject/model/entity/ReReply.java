package com.example.zproject.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

//대댓글 엔티티
@Entity
@Data
public class ReReply {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer reReplyId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "replyId")
    private Reply repliesId;

    private String reReplyWriter;
    private String reReplyContent;
    private LocalDateTime reReplyDate;

}
