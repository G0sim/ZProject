package com.example.zproject.model.dto.request;

import lombok.Data;

@Data
public class ModifyInfoRequest {

    String email;
    String password;
    String nick;
    String userImage;
}
