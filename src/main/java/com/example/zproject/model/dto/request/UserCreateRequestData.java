package com.example.zproject.model.dto.request;

import com.example.zproject.model.entity.User;
import jakarta.validation.constraints.Email;
import lombok.*;

import java.time.LocalDateTime;

//유저 계정생성 request dto
@Data
public class UserCreateRequestData {

    Integer userId;
    @Email
    String email;
    String password;
    String nick;
    String userImage;
    LocalDateTime joinData;
    String authority;
    String socialToken;

}
