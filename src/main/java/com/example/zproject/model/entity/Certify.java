package com.example.zproject.model.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;

//이메일 인증 관련 엔티티
@Entity
@Data
public class Certify {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer certifyId;
    private String email;
    private String code;
    private String status;
    private LocalDateTime sendTime;
}
