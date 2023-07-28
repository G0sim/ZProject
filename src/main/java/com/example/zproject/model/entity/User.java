package com.example.zproject.model.entity;

import com.example.zproject.model.dto.request.ModifyInfoRequest;
import com.example.zproject.model.dto.request.UserCreateRequestData;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

//유저 정보 엔티티
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer userId;
    private String email;
    private String password;
    private String nick;
    private String userImage;
    private LocalDateTime joinDate;
    private String authority;
    private String socialToken;


    @OneToMany(mappedBy = "postWriter", fetch = FetchType.LAZY)
    private List<Post> postList;



    @OneToMany(mappedBy = "replyWriter", fetch = FetchType.LAZY)
    private List<Reply> replyList;
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Like> likeList;
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Chat> chatList;
    @OneToMany(mappedBy = "avatarName", fetch = FetchType.LAZY)
    private List<Avatar> avatarList;

    // 회원가입시 사용하는 생성자
    public User(UserCreateRequestData dto) {
        this.email = dto.getEmail();
        this.password = dto.getPassword();
        this.nick = dto.getNick();
        this.userImage = dto.getUserImage();
        this.joinDate = LocalDateTime.now();
    }

    //유저 정보 수정시 사용하는 생성자
    public User(ModifyInfoRequest dto) {
        this.password = dto.getPassword();
        this.nick = dto.getNick();
        this.userImage = dto.getUserImage();
    }

}