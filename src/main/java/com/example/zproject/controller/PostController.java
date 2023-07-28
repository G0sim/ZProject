package com.example.zproject.controller;

import com.example.zproject.exception.NonExistentPostException;
import com.example.zproject.exception.NotExistUserException;
import com.example.zproject.exception.NotNullAddPostException;
import com.example.zproject.model.dto.PostDetail;
import com.example.zproject.model.dto.request.AddPostRequest;
import com.example.zproject.model.dto.request.ModifyPostRequest;
import com.example.zproject.model.dto.request.PostWriterRequest;
import com.example.zproject.model.dto.response.PostListResponse;
import com.example.zproject.model.entity.Post;
import com.example.zproject.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/post")
public class PostController {

    private final PostService postService;

    //신규 글쓰기 API
    @PostMapping("/write")
    public ResponseEntity<?> addPost(AddPostRequest areq, PostWriterRequest preq) throws NotExistUserException, IOException, NotNullAddPostException {
        postService.addNewPost(areq, preq);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    //글 수정하기
    @PostMapping("/re-write")
    public ResponseEntity<?> reWritePost(ModifyPostRequest mreq, PostWriterRequest preq, @RequestParam Integer postId) throws NotExistUserException, NonExistentPostException, IOException, NotNullAddPostException {
        postService.modifyPost(mreq, preq, postId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    //글 목록 보이는 API
    @GetMapping("/board")
    public ResponseEntity<?> viewBoard(@RequestParam(defaultValue = "1") int page) {
        Long total = postService.size();
        List<PostDetail> posts = postService.viewBoardList(page);
        PostListResponse response = new PostListResponse(total, posts);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //상세글보기
    @GetMapping("/detail")
    public ResponseEntity<?> readSpecificPostHandle(@RequestParam Integer postId) throws NonExistentPostException {

        PostDetail postDetail = postService.viewPostDetail(postId);

        return new ResponseEntity<>(postDetail, HttpStatus.OK);
    }

    //글 삭제하기
    @GetMapping("/delete")
    public ResponseEntity<?> deletePost() {

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
