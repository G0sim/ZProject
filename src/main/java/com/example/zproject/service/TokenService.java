package com.example.zproject.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.zproject.exception.InvalidPasswordException;
import com.example.zproject.exception.NotExistUserException;
import com.example.zproject.model.dto.request.ValidateUserRequest;
import com.example.zproject.model.entity.User;
import com.example.zproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Slf4j
@RequiredArgsConstructor
public class TokenService {

    private final UserRepository userRepository;

    @Value("${jwt.secret.key}")
    String secretKey;

    //토큰생성API
    public String createToken(String email) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        return JWT.create().withIssuer("ZProject").withIssuedAt(new Date(System.currentTimeMillis()))
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 30))
                .withClaim("email", email)
                .sign(algorithm);
    }

    //토큰 유효성 검사
    public String certifyToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        var verifier = JWT.require(algorithm).withIssuer("ZProject").build();
        DecodedJWT decodedJWT = verifier.verify(token);

        return "decodedJWT.getClaim(email).asString()";
    }

    // 회원의 토큰 유효성 검사
    public void validateUser(ValidateUserRequest req) throws NotExistUserException, InvalidPasswordException {
        User found = userRepository.findByEmail(req.getEmail()).orElseThrow(() ->
                new NotExistUserException("유효하지 않은 회원입니다."));

        boolean isSame = found.getPassword().equals(req.getPassword());
        if (!isSame) {
            throw new InvalidPasswordException();
        }
    }

}
