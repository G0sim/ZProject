package com.example.zproject.controller;

import com.example.zproject.exception.InvalidPasswordException;
import com.example.zproject.exception.NotCertifyException;
import com.example.zproject.exception.ExistUserEmailException;
import com.example.zproject.exception.NotExistUserException;
import com.example.zproject.model.dto.request.CheckMailRequest;
import com.example.zproject.model.dto.request.ModifyInfoRequest;
import com.example.zproject.model.dto.request.UserCreateRequestData;
import com.example.zproject.model.dto.request.ValidateUserRequest;
import com.example.zproject.model.dto.response.ValidateUserResponse;
import com.example.zproject.service.TokenService;
import com.example.zproject.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


//유저와 관련된 작업을 처리할 컨트롤러
@RestController
@Slf4j
@RequestMapping("/user")
@RequiredArgsConstructor
@CrossOrigin
public class UserController {

    private final UserService userService;
    private final TokenService tokenService;

    //회원가입 처리할 controller method
    @PostMapping("/signup")
    public ResponseEntity<?> userSignUpHandle(@Valid UserCreateRequestData ureq)
            throws ExistUserEmailException, NotCertifyException {

        userService.createUser(ureq);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    //이메일 사용 가능 여부 확인하기
    @GetMapping("/available")
    public ResponseEntity<?> checkToAvailableMail(CheckMailRequest req) throws ExistUserEmailException {

        userService.checkToAvailableMail(req);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    //유저가 로그인하면서 토큰 발급
    @PostMapping("/login")
    public ResponseEntity<?> userLogInHandle(ValidateUserRequest req)throws NotExistUserException,InvalidPasswordException{

        tokenService.validateUser(req);
        String token = tokenService.createToken(req.getEmail());
        var response = new ValidateUserResponse(200, token, req.getEmail());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //유저 정보 수정
    @PostMapping("/modify")
    public ResponseEntity<?> modifyUserInfo(String password, ModifyInfoRequest req)throws InvalidPasswordException, NotExistUserException {
        if(!password.equals(req.getPassword())){
            throw new InvalidPasswordException("비밀번호를 확인해주세요");
        }
        userService.modifyUserInformation(req);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
