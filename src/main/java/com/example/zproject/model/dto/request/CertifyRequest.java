package com.example.zproject.model.dto.request;

import lombok.Data;
import org.hibernate.validator.internal.util.privilegedactions.LoadClass;

import java.time.LocalDateTime;

//인증메일 관리하는 request dto
@Data
public class CertifyRequest {

    Integer certifyId;
    String email;
    String code;
    String status;
    LocalDateTime sendTime;

}
