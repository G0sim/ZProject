package com.example.zproject.model.dto.response;


import com.example.zproject.model.entity.Certify;
import lombok.Data;

import java.time.LocalDateTime;

//인증메일 관리하는 response dto
@Data
public class CertifyResponse {

    Integer certifyId;
    String email;
    String code;
    String status;
    LocalDateTime sendTime;

    public CertifyResponse(Certify entity) {
        this.certifyId = entity.getCertifyId();
        this.email = entity.getEmail();
        this.code = entity.getCode();
        this.status = entity.getStatus();
        this.sendTime = entity.getSendTime();
    }
}
