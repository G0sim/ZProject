package com.example.zproject.service;

import com.example.zproject.exception.NonExistentPostException;
import com.example.zproject.exception.NotExistUserException;
import com.example.zproject.exception.NotNullAddPostException;
import com.example.zproject.model.dto.request.*;
import com.example.zproject.model.entity.ReReply;
import com.example.zproject.model.entity.Reply;
import com.example.zproject.model.entity.User;
import com.example.zproject.repository.ReReplyRepository;
import com.example.zproject.repository.ReplyRepository;
import com.example.zproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ReplyService {

    private final ReplyRepository replyRepository;
    private final ReReplyRepository reReplyRepository;
    private final UserRepository userRepository;

    //신규 댓글 작성하기
    @Transactional
    public void addNewReply(PostWriterRequest preq, AddReplyRequest areq) throws NotExistUserException,NotNullAddPostException {

        User user = userRepository.findByEmail(preq.getEmail()).orElseThrow(() -> new NotExistUserException("등록된 회원이 아닙니다."));

        if(areq.getReplyContent()==null){
            throw new NotNullAddPostException("내용을 입력해주세요.");
        }

        var reply = new Reply();
        reply.setReplyWriter(user);
        reply.setPostId(areq.getPostId());
        reply.setReplyContent(areq.getReplyContent());
        reply.setReplyDate(LocalDateTime.now());

        replyRepository.save(reply);

    }

    //작성했던 댓글 수정하기
    @Transactional
    public void modifyReply(PostWriterRequest preq, ModifyReplyRequest mreq) throws NotNullAddPostException,NotExistUserException, NonExistentPostException {
        User user = userRepository.findByEmail(preq.getEmail()).orElseThrow(() -> new NotExistUserException("등록된 회원이 아닙니다."));
        Reply reply = replyRepository.findById(mreq.getReplyId()).orElseThrow(() -> new NonExistentPostException("등록되지 않은 댓글입니다."));

        if (!reply.getReplyWriter().getUserId().equals(user.getUserId())) {
            throw new NonExistentPostException("본인이 등록한 글만 삭제할 수 있습니다.");
        }

        if(mreq.getReplyContent()==null){
            throw new NotNullAddPostException("내용을 입력해주세요.");
        }

        Reply updateReply = replyRepository.findById(mreq.getReplyId()).orElse(null);
        updateReply.setReplyContent(mreq.getReplyContent());
        replyRepository.save(updateReply);
    }

    //새로운 대댓글 작성하기
    @Transactional
    public void addNewReReply(PostWriterRequest preq, AddReReplyRequest areq) throws NotNullAddPostException, NotExistUserException {
        User user = userRepository.findByEmail(preq.getEmail()).orElseThrow(() -> new NotExistUserException("등록된 회원이 아닙니다."));

        if(areq.getReReplyContent()==null){
            throw new NotNullAddPostException("내용을 입력해주세요.");
        }

        var reReply = new ReReply();
        reReply.setReReplyWriter(String.valueOf(user.getUserId()));
        reReply.setReReplyContent(areq.getReReplyContent());
        reReply.setReReplyDate(LocalDateTime.now());

        reReplyRepository.save(reReply);
    }

    //작성했던 대댓글 수정하기
    @Transactional
    public  void modifyReReply(PostWriterRequest preq, ModifyReReplyRequest mreq)throws NotNullAddPostException,NotExistUserException, NonExistentPostException{
        User user = userRepository.findByEmail(preq.getEmail()).orElseThrow(() -> new NotExistUserException("등록된 회원이 아닙니다."));
        ReReply reReply = reReplyRepository.findById(mreq.getReReplyId()).orElseThrow(()-> new NonExistentPostException("등록되지 않은 대댓글입니다."));

        String u = String.valueOf(user.getUserId());

        if(!reReply.getReReplyWriter().equals(u)){
            throw new NonExistentPostException("본인이 등록한 글만 삭제할 수 있습니다.");
        }

        if(mreq.getReReplyContent()==null){
            throw new NotNullAddPostException("내용을 입력해주세요.");
        }

        ReReply updateReReply = reReplyRepository.findById(mreq.getReReplyId()).orElse(null);
        updateReReply.setReReplyContent(mreq.getReReplyContent());
        reReplyRepository.save(updateReReply);
    }
}
