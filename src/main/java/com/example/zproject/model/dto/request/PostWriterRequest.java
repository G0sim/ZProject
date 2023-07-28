package com.example.zproject.model.dto.request;

import com.example.zproject.model.entity.User;
import lombok.Data;


@Data
public class PostWriterRequest {

    Integer userId;
    String email;
    String nick;

}