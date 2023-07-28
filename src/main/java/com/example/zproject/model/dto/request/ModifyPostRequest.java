package com.example.zproject.model.dto.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class ModifyPostRequest {

    private String cate;
    private String title;
    private String postContent;
    private List<MultipartFile> images;
}
