package com.example.zproject.model.dto.response;

import com.example.zproject.model.dto.PostDetail;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PostListResponse {
    private Long total;
    private List<PostDetail> posts;
}
