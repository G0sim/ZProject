package com.example.zproject.repository;

import com.example.zproject.model.entity.Certify;
import com.example.zproject.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CertifyRepository extends JpaRepository<Certify, Integer> {

    Optional<Certify> findByEmail(String email);
//    Optional<Certify> findAllByEmail(String email);
}
