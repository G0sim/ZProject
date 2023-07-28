package com.example.zproject.model.dto;

import com.example.zproject.model.entity.Image;
import com.example.zproject.model.entity.Post;
import com.example.zproject.model.entity.User;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Data
class PostWriter {
    private final String nick;
    PostWriter(User user) {
        this.nick = user.getNick();
    }
}


@Data
public class PostDetail {

    @Id
    Integer postId;
    String cate;
    String title;
    PostWriter postWriter;
    String postContent;
    String postDate;
    Integer views;

    public PostDetail(Post p) {
        super();
        this.postId = p.getPostId();
        this.cate = p.getCate();
        this.title = p.getTitle();
        this.postWriter = new PostWriter(p.getPostWriter());
        this.postContent = p.getPostContent();

//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
//        this.postDate = dateFormat.format(p.getPostDate());
        this.postDate = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일").format(p.getPostDate());

        this.views = p.getViews();
    }
}
