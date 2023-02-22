package com.example.springlv4.service;

import com.example.springlv4.dto.BoardResponseDto;
import com.example.springlv4.dto.CommentRequestDto;
import com.example.springlv4.dto.CommentResponseDto;
import com.example.springlv4.entity.Board;
import com.example.springlv4.entity.Comment;
import com.example.springlv4.entity.User;
import com.example.springlv4.repository.BoardRepository;
import com.example.springlv4.repository.CommentRepository;
import com.example.springlv4.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.example.springlv4.entity.UserRoleEnum.ADMIN;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;

    public CommentResponseDto commentwrite(Long id, CommentRequestDto commentRequestDto, UserDetailsImpl userDetails) {
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("게시판이 없습니다")
        );
        Comment comment = commentRepository.saveAndFlush(new Comment(commentRequestDto,board,userDetails.getUser()));
        return new CommentResponseDto(comment);
    }

    public CommentResponseDto commentrevise(Long id, CommentRequestDto commentRequestDto, UserDetailsImpl userDetails) {
        User user= userDetails.getUser();
        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("게시판이 없습니다")
        );
        if(Objects.equals(user.getId(), comment.getUser().getId())){
            comment.update(commentRequestDto);
        }else{
            if(user.getRole() == ADMIN){
                comment.update(commentRequestDto);
            }else{
                throw new IllegalArgumentException("사용자가 작성한 댓글만 수정가능합니다");
            }
        }
        return new CommentResponseDto(comment);
    }


    public Map<String, Object> commentdelete(Long id, UserDetailsImpl userDetails) {
        User user= userDetails.getUser();
        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("게시판이 없습니다")
        );
        if(Objects.equals(user.getId(), comment.getUser().getId())){
            commentRepository.deleteById(id);
        }else{
            if(user.getRole() == ADMIN){
                commentRepository.deleteById(id);
            }else{
                throw new IllegalArgumentException("사용자가 작성한 댓글만 수정가능합니다");
            }
        }
        Map<String,Object> subject = new HashMap<>();
        subject.put("Success","true");
        return subject;
    }
}
