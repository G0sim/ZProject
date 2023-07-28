package com.example.zproject.model.entity;


import jakarta.persistence.*;

//실시간 채팅 엔티티
@Entity
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer chatId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;
    private String chatMessage;
}
