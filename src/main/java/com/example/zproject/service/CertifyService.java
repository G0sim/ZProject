package com.example.zproject.service;

import com.example.zproject.exception.AlreadyCertifyException;
import com.example.zproject.exception.NotCertifyException;
import com.example.zproject.model.dto.request.CertifyCodeRequset;
import com.example.zproject.model.entity.Certify;
import com.example.zproject.repository.CertifyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class CertifyService {

    private final CertifyRepository certifyRepository;

    //인증메일 보내는 API
    @Transactional
    public void sendCertifyMail(String email) throws AlreadyCertifyException {
        //이메일 인증 여부 확인하기
        var found = certifyRepository.findByEmail(email).orElse(null);
        int codeNumber = (int) (Math.random() * 1_000_000);
        String code = String.format("%06d", codeNumber);

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        String mailTxt = """
                <div>
                    <div> 안녕하세요 <span>'#오운완'</span> 입니다</div>
                    <div> 본인 인증 절차에 따라 인증코드를 보내드립니다.
                          아래 인증번호를 정확하게 기입해주세요. </div>
                    <div> 인증코드 : #{code} </div>
                </div>
                                    
                                    
                """.replace("#{code}", code);

        mailMessage.setTo(email);
        mailMessage.setFrom("no-reply@gmail.com");
        mailMessage.setSubject("[#오운완]에서 인증메일 보내드립니다.");
        mailMessage.setText(mailTxt);

        //메일 인증을 했다면
        if (found != null && found.getStatus().equals("Passed")) {
            throw new AlreadyCertifyException("이미 인증한 사용자입니다.");
        }

        //인증 메일을 받았지만 인증 하지 않았다면
        if (found != null && found.getStatus().equals("Yet")) {
            var certify = certifyRepository.findByEmail(email).orElse(null);
            certify.setCode(code);
            certify.setSendTime(LocalDateTime.now());
            certifyRepository.save(certify);
        }

        //인증메일을 한번도 받은 적이 없다면
        if (found == null) {
            Certify certify = new Certify();
            certify.setCode(code);
            certify.setEmail(email);
            certify.setSendTime(LocalDateTime.now());
            certify.setStatus("Yet");
            certifyRepository.save(certify);
        }

    }

    //인증메일을 검증 처리하는 service method
    @Transactional
    public void certifyConfirmCode(CertifyCodeRequset req) throws NotCertifyException {
        Optional<Certify> result = certifyRepository.findByEmail(req.getEmail());
        Certify found = result.orElseThrow(() -> new NotCertifyException("인증코드를 발급받은 적이 없습니다."));

        LocalDateTime time1 = LocalDateTime.now();
        LocalDateTime time2 = found.getSendTime();

        long limit = Duration.between(time1, time2).toMillis();
        long time = 1000 * 60 * 3;

        if (limit > time) {
             throw new NotCertifyException("인증코드 유효시간이 만료되었습니다.");
        }

        if (!found.getCode().equals(req.getCode())) {
            throw new NotCertifyException("인증코드가 일치하지 않습니다.");
        }

        found.setStatus("Passed");
        certifyRepository.save(found);
    }
}
