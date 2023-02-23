package com.example.springlv4.controller;

import com.example.springlv4.dto.BoardHeartResponseDto;
import com.example.springlv4.dto.BoardRequestDto;
import com.example.springlv4.dto.BoardResponseDto;
import com.example.springlv4.security.UserDetailsImpl;
import com.example.springlv4.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class BoardController {

    private final BoardService boardService;

    @PostMapping("/write")
    public ResponseEntity boardwrite(@RequestBody BoardRequestDto boardRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
         BoardResponseDto board = boardService.boardwrite(boardRequestDto, userDetails.getUser().getUsername());
         return  ResponseEntity.ok(board);
    }
    @GetMapping("/read")
    public List<BoardHeartResponseDto> boardread(){
        return boardService.boardread();
    }
    @PutMapping("/revise/{id}")
    public BoardResponseDto boardrevise(@PathVariable Long id, @RequestBody BoardRequestDto boardRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return boardService.boardrevise(id,boardRequestDto,userDetails);
    }
    @DeleteMapping("/delete/{id}")
    public Map<String, Object> boardDelete(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return boardService.boarddelete(id,userDetails);
    }
}
