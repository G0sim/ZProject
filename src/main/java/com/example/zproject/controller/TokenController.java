//package com.example.zproject.controller;
//
//
//import com.example.zproject.exception.InvalidPasswordException;
//import com.example.zproject.exception.NotExistUserException;
//import com.example.zproject.model.dto.request.ValidateUserRequest;
//import com.example.zproject.model.dto.response.ValidateUserResponse;
//import com.example.zproject.service.TokenService;
//import com.example.zproject.service.UserService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.Base64;
//
//@RestController
//@RequiredArgsConstructor
//@CrossOrigin
//@RequestMapping("/tk")
//public class TokenController {
//
//    private final UserService userService;
//    private final TokenService tokenService;
//
//    //토큰 발급해주는 API
//    @PostMapping("/validate")
//    public ResponseEntity<?> issuanceToken(ValidateUserRequest req) throws NotExistUserException, InvalidPasswordException {
//
//        tokenService.validateUser(req);
//        String token = tokenService.createToken(req.getEmail());
//        var response = new ValidateUserResponse(200, token, req.getEmail());
//
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }
//}
////헤더 > Authorization //키값