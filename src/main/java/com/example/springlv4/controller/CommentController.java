package com.example.springlv4.controller;

import com.example.springlv4.dto.CommentRequestDto;
import com.example.springlv4.dto.CommentResponseDto;
import com.example.springlv4.security.UserDetailsImpl;
import com.example.springlv4.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/c/write/{id}")
    public CommentResponseDto Commentwrite(@PathVariable Long id, @RequestBody CommentRequestDto commentRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return commentService.commentwrite(id, commentRequestDto, userDetails);
    }


    @PutMapping("/c/revise/{id}")
    public CommentResponseDto Commentrevise(@PathVariable Long id, @RequestBody CommentRequestDto commentRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return commentService.commentrevise(id, commentRequestDto, userDetails);
    }


    @DeleteMapping("/c/delete/{id}")
    public Map<String, Object> Commentdelete(@PathVariable Long id,@AuthenticationPrincipal UserDetailsImpl userDetails){
        return commentService.commentdelete(id,userDetails);
    }
}
