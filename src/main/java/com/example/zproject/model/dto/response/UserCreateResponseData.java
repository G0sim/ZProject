package com.example.zproject.model.dto.response;

import com.example.zproject.model.entity.User;

import java.time.LocalDateTime;

//유저 계정 생성 response dto

public class UserCreateResponseData {

    Integer userId;
    String email;
    String password;
    String nick;
    String userImage;
    LocalDateTime joinData;
    String authority;
    String socialToken;
    LocalDateTime sendTime;

    public UserCreateResponseData(User entity) {
        super();
        this.userId = entity.getUserId();
        this.email = entity.getEmail();
        this.password = entity.getPassword();
        this.nick = entity.getNick();
        this.userImage = entity.getUserImage();
        this.joinData = entity.getJoinDate();
        this.authority = entity.getAuthority();
        this.socialToken = entity.getSocialToken();
    }

}
