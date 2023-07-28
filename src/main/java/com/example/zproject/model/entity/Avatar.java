package com.example.zproject.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Avatar {

    @Id
    private Integer avatarId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="userImage")
    private User avatarName;
    private String Url;

}
