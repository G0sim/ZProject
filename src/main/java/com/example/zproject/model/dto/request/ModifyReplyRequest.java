package com.example.zproject.model.dto.request;

import lombok.Data;

@Data
public class ModifyReplyRequest {

    private Integer replyId;
    private String replyContent;
}
