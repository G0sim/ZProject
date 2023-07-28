package com.example.zproject.service;

import com.example.zproject.exception.NotCertifyException;
import com.example.zproject.exception.ExistUserEmailException;
import com.example.zproject.exception.NotExistUserException;
import com.example.zproject.model.dto.request.CheckMailRequest;
import com.example.zproject.model.dto.request.ModifyInfoRequest;
import com.example.zproject.model.dto.request.UserCreateRequestData;
import com.example.zproject.model.entity.Certify;
import com.example.zproject.model.entity.User;
import com.example.zproject.repository.CertifyRepository;
import com.example.zproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

//유저와 관련된 작업을 처리할 서비스
@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final CertifyRepository certifyRepository;

    //회원가입 처리할 service method
    @Transactional
    public void createUser(UserCreateRequestData ucrd)
            throws ExistUserEmailException, NotCertifyException {
        Optional<User> findUser = userRepository.findByEmail(ucrd.getEmail());
        Optional<Certify> optional = certifyRepository.findByEmail(ucrd.getEmail());
        //이메일 중복 검사
        if (findUser.isPresent()) {
            throw new ExistUserEmailException("이미 등록된 이메일입니다. 다른 이메일을 사용해주세요.");
        }

        if (optional.isPresent()) {
            if (optional.get().getStatus() == null) {
                throw new ExistUserEmailException("이메일 인증을 해주세요.");
            }
        }

        //이메일이 중복되지 않고, 인증이 되어 있으면 회원가입 절차 진행
        User saved = userRepository.save(new User(ucrd));

    }

    //메일 중복 여부 검사해주는 service method
    @Transactional
    public void checkToAvailableMail(CheckMailRequest req) throws ExistUserEmailException {
        boolean result = userRepository.existsByEmail(req.getEmail());
        if (result) {
            throw new ExistUserEmailException("이미 사용중인 이메일입니다. 다른 이메일을 입력해주세요.");
        }
    }

    //유저 정보 수정하는 service
    @Transactional
    public void modifyUserInformation(ModifyInfoRequest req) throws NotExistUserException {
        Optional<User> user = userRepository.findByEmail(req.getEmail());
        if (user.isEmpty()) {
            throw new NotExistUserException("등록된 회원이 아닙니다");
        }

        User updateUser = userRepository.findByEmail(req.getEmail()).orElse(null);
        updateUser.setPassword(req.getPassword());
        updateUser.setNick(req.getNick());
        updateUser.setUserImage(req.getUserImage());

        // if 유저의 기본키값이 디비에 있다면 업데이트 else 없다면 새로 세이브
        userRepository.save(updateUser);
    }

}
