package com.example.zproject.model.dto.request;

import com.example.zproject.model.entity.Reply;
import com.example.zproject.model.entity.User;
import lombok.Data;

@Data
public class AddReReplyRequest {

    private Integer reReplyId;
    private User reReplyWriter;
    private Reply replyId;
    private String reReplyContent;

}
