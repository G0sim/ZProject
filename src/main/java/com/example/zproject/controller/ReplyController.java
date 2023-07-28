package com.example.zproject.controller;


import com.example.zproject.exception.NonExistentPostException;
import com.example.zproject.exception.NotExistUserException;
import com.example.zproject.exception.NotNullAddPostException;
import com.example.zproject.model.dto.request.AddReplyRequest;
import com.example.zproject.model.dto.request.ModifyReplyRequest;
import com.example.zproject.model.dto.request.PostWriterRequest;
import com.example.zproject.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/reply")
public class ReplyController {

    private final ReplyService replyService;

    //신규 댓글 작성하기
    @PostMapping("/write")
    public ResponseEntity<?> writeReply(PostWriterRequest preq, AddReplyRequest areq) throws NotNullAddPostException, NotExistUserException {
        replyService.addNewReply(preq, areq);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    //작성했던 댓글 수정하기
    @PostMapping("/modify")
    public ResponseEntity<?> modifyReply(PostWriterRequest preq, ModifyReplyRequest mreq) throws NotNullAddPostException, NotExistUserException, NonExistentPostException {
        replyService.modifyReply(preq, mreq);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    //신규 대댓글 작성하기
    @PostMapping("/write-rerply")
    public ResponseEntity<?> writeReReply(){

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    //작성했던 대댓글 수정하기
    @PostMapping("/modify-rereply")
    public ResponseEntity<?> modifyReReply(){

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
