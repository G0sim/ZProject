package com.example.zproject.repository;

import com.example.zproject.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    public Optional<User> findByEmail(String email);

    public boolean existsByEmail(String email);
}
