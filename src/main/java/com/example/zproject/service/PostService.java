package com.example.zproject.service;

import java.io.File;

import com.example.zproject.exception.NonExistentPostException;
import com.example.zproject.exception.NotExistUserException;
import com.example.zproject.exception.NotNullAddPostException;
import com.example.zproject.model.dto.PostDetail;
import com.example.zproject.model.dto.request.AddPostRequest;
import com.example.zproject.model.dto.request.ModifyPostRequest;
import com.example.zproject.model.dto.request.PostWriterRequest;
import com.example.zproject.model.entity.Image;
import com.example.zproject.model.entity.Post;
import com.example.zproject.model.entity.User;
import com.example.zproject.repository.ImageRepository;
import com.example.zproject.repository.PostRepository;
import com.example.zproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final ImageRepository imageRepository;

    @Value("${upload.server}")
    private String uploadServer;
    @Value("${upload.basedir}")
    private String uploadBaseDir;

    //신규 글쓰기 service
    @Transactional
    public void addNewPost(AddPostRequest areq, PostWriterRequest preq) throws NotNullAddPostException, NotExistUserException, IOException {

        User user = userRepository.findByEmail(preq.getEmail()).orElseThrow(() -> new NotExistUserException("등록된 회원이 아닙니다."));

        if (areq.getCate() == null) {
            throw new NotNullAddPostException("카테고리를 설정해주세요.");
        }

        if (areq.getTitle() == null) {
            throw new NotNullAddPostException("제목을 입력해주세요");
        }
        var post = new Post();
        post.setPostWriter(user);
        post.setCate(areq.getCate());
        post.setTitle(areq.getTitle());
        post.setPostContent(areq.getPostContent());
        post.setPostDate(LocalDateTime.now());
        post.setViews(0);

        var add = postRepository.save(post);

        if (areq.getImages() != null) {
            File uploadDirectory = new File(uploadBaseDir + "/post/" + add.getPostId());
            boolean mkdirs = uploadDirectory.mkdirs();

            for (MultipartFile multi : areq.getImages()) {
                String fileName = String.valueOf(System.currentTimeMillis());
                String extension = multi.getOriginalFilename().split("\\.")[1];
                File dest = new File(uploadDirectory, fileName + "." + extension);

                multi.transferTo(dest);

                Image image = new Image();
                image.setType(multi.getContentType());
                image.setImageUrl(uploadServer + "/resource/post/" + add.getPostId() + "/" + fileName + "." + extension);
                image.setPosts(post);
                imageRepository.save(image);
            }
        }
    }

    //작성한 글 수정하기
    @Transactional
    public void modifyPost(ModifyPostRequest mreq, PostWriterRequest preq, Integer postId) throws NotNullAddPostException, NotExistUserException, NonExistentPostException, IOException {
        User user = userRepository.findByEmail(preq.getEmail()).orElseThrow(() -> new NotExistUserException("등록된 회원이 아닙니다."));
        Post post = postRepository.findById(postId).orElseThrow(() -> new NonExistentPostException("등록되지 않은 게시글입니다."));

        if (!post.getPostWriter().getUserId().equals(user.getUserId())) {
            throw new NonExistentPostException("본인이 등록한 글만 삭제할 수 있습니다");
        }

        if (mreq.getCate() == null) {
            throw new NotNullAddPostException("카테고리를 설정해주세요.");
        }

        if (mreq.getTitle() == null) {
            throw new NotNullAddPostException("제목을 입력해주세요");
        }


        Post updatePost = postRepository.findById(postId).orElse(null);
        updatePost.setCate(mreq.getCate());
        updatePost.setTitle(mreq.getTitle());
        updatePost.setPostContent(mreq.getPostContent());

        var update = postRepository.save(updatePost);

        if (mreq.getImages() != null) {
            String uploadDirectory = uploadBaseDir + "/post/" + update.getPostId();
            for (MultipartFile multi : mreq.getImages()) {
                String fileName = String.valueOf(System.currentTimeMillis());
                String extension = multi.getOriginalFilename().split("\\.")[1];
                File dest = new File(uploadDirectory, fileName + "." + extension);

                multi.transferTo(dest);

                Image image = imageRepository.findByPosts_PostId(postId);
                image.setType(multi.getContentType());
                image.setImageUrl(uploadServer + "/resource/post/" + update.getPostId() + "/" + fileName + "." + extension);
                image.setPosts(post);
                imageRepository.save(image);
            }
        }
    }

    //등록된 글들의 전체 개수 구하기
    public Long size() {
        return postRepository.count();
    }

    //글 목록 뽑아오기
    @Transactional
    public List<PostDetail> viewBoardList(@RequestParam(defaultValue = "1") int page) {
        List<Post> postList = postRepository.findAll(PageRequest.of(page - 1, 10, Sort.by("postDate").descending())).toList();
        return postList.stream().map(PostDetail::new).toList();
    }


    //글 상세보기
    @Transactional
    public PostDetail viewPostDetail(@RequestParam Integer postId) throws NonExistentPostException {
        Post post = postRepository.findById(postId).orElseThrow(() -> new NonExistentPostException("등록되지 않은 글입니다."));

        return new PostDetail(post);
    }

    //글 삭제하기

}
