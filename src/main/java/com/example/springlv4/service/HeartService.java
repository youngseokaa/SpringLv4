package com.example.springlv4.service;

import com.example.springlv4.dto.HeartRequestDto;
import com.example.springlv4.dto.HeartResponseDto;
import com.example.springlv4.dto.HeartsResponseDto;
import com.example.springlv4.dto.SignupResponseDto;
import com.example.springlv4.entity.Board;
import com.example.springlv4.entity.Comment;
import com.example.springlv4.entity.Heart;
import com.example.springlv4.entity.Hearts;
import com.example.springlv4.repository.BoardRepository;
import com.example.springlv4.repository.CommentRepository;
import com.example.springlv4.repository.HeartRepository;
import com.example.springlv4.repository.HeartsRepository;
import com.example.springlv4.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor

public class HeartService {
    private final HeartRepository heartRepository;
    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;
    private final HeartsRepository heartsRepository;

    @Transactional
    public HeartResponseDto heart(HeartRequestDto heartRequestDto, Long id, UserDetailsImpl userDetails) {
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("보드가 존재하지 않습니다")
        );
        heartRequestDto.setHeart(!heartRequestDto.isHeart());
        Heart heart = (new Heart(heartRequestDto, board, board.getUser()));
        if (heartRequestDto.isHeart() == true) {
            heartRepository.saveAndFlush(new Heart(heartRequestDto, board, userDetails.getUser()));
            if (heartRepository.findByUser_IdAndBoard_Id(userDetails.getUser().getId(), board.getId()).isPresent()) {
            }
        } else {
            heartRepository.deleteById(userDetails.getUser().getId());
            return null;
        }
        return new HeartResponseDto(heart);
    }

    public HeartsResponseDto hearts(HeartRequestDto heartRequestDto, Long id, UserDetailsImpl userDetails) {
        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("보드가 존재하지 않습니다")
        );
        heartRequestDto.setHeart(!heartRequestDto.isHeart());
        Hearts heart = (new Hearts(heartRequestDto, comment, comment.getUser(),comment.getBoard()));
        if (heartRequestDto.isHeart() == true) {
            heartsRepository.saveAndFlush(new Hearts(heartRequestDto, comment, userDetails.getUser(),comment.getBoard()));
            if (heartsRepository.findByUser_IdAndComment_Id(userDetails.getUser().getId(), comment.getId()).isPresent()) {
            }
        } else {
            heartsRepository.deleteById(userDetails.getUser().getId());
            return null;
        }
        return new HeartsResponseDto(heart);
    }
}