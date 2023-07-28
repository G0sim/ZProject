package com.example.zproject.model.dto.request;

import lombok.Data;

@Data
public class ValidateUserRequest {

    private String email;
    private String password;
}
