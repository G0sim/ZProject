package com.example.zproject.model.dto.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CertifyCodeRequset {
    String email;
    String code;
    LocalDateTime sendTime;
}
