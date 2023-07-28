package com.example.zproject.controller;

import com.example.zproject.exception.InvalidPasswordException;
import com.example.zproject.exception.NotCertifyException;
import com.example.zproject.model.dto.request.CertifyCodeRequset;
import com.example.zproject.model.dto.response.CertifyEmailResponse;
import com.example.zproject.service.CertifyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@CrossOrigin
@RequestMapping("/certify")
public class CertifyController {

    private final CertifyService certifyService;

    //이메일 인증코드를 발급해주는 API
    @PostMapping("/send-mail")
    public ResponseEntity<?> sendCertifyMail(String email) throws Exception {

        certifyService.sendCertifyMail(email);
        var response = new CertifyEmailResponse(200, "이메일 코드가 정상 발급되었습니다.");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //이메일 인증코드를 검증해주는 API
    @PatchMapping("/confirm-code")
    public ResponseEntity<?> confirmTheCode(CertifyCodeRequset req) throws NotCertifyException {

        certifyService.certifyConfirmCode(req);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
