package com.example.zproject.model.dto.request;

import com.example.zproject.model.entity.Post;
import com.example.zproject.model.entity.User;
import lombok.Data;

@Data
public class AddReplyRequest {

    private Integer replyId;
    private User replyWriter;
    private Post postId;
    private String replyContent;

}
