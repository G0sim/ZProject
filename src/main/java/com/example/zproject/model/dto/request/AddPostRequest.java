package com.example.zproject.model.dto.request;

import com.example.zproject.model.entity.User;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class AddPostRequest {

    private Integer postId;
    private User postWriter;
    private String cate;
    private String title;
    private String postContent;
    private LocalDateTime postDate;
    private Integer view;
    private List<MultipartFile> images;


}
