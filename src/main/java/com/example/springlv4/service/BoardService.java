package com.example.springlv4.service;

import com.example.springlv4.dto.BoardHeartResponseDto;
import com.example.springlv4.dto.BoardRequestDto;
import com.example.springlv4.dto.BoardResponseDto;
import com.example.springlv4.entity.Board;
import com.example.springlv4.entity.User;
import com.example.springlv4.repository.BoardRepository;
import com.example.springlv4.repository.UserRepository;
import com.example.springlv4.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.example.springlv4.entity.UserRoleEnum.ADMIN;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final UserRepository userRepository;
    private final BoardRepository boardRepository;

    public BoardResponseDto boardwrite(BoardRequestDto boardRequestDto,String username) {
        User user = userRepository.findByUsername(username).orElseThrow(
                ()-> new IllegalArgumentException("사용자가 존재하지 않습니다")
        );
        Board board = boardRepository.saveAndFlush(new Board(boardRequestDto,user));
        return new BoardResponseDto(board, board.getUser());
    }

    public  List<BoardHeartResponseDto> boardread() {
        List<Board> board = boardRepository.findAll();
        List<BoardHeartResponseDto> boardResponseDtos = new ArrayList<>();
        Collections.reverse(board);
        for (Board value : board) {
            boardResponseDtos.add(new BoardHeartResponseDto(value));
        }
        return boardResponseDtos;
    }

    public BoardResponseDto boardrevise(Long id,BoardRequestDto boardRequestDto, UserDetailsImpl userDetails) {
         User user= userDetails.getUser();
         Board board = boardRepository.findById(id).orElseThrow(
                 () -> new IllegalArgumentException("게시판이 없습니다")
         );
        if(Objects.equals(user.getId(), board.getUser().getId())){
            board.update(boardRequestDto);
        }else{
            if(user.getRole() == ADMIN){
                board.update(boardRequestDto);
            }else{
                throw new IllegalArgumentException("사용자가 작성한 댓글만 수정가능합니다");
            }
        }
        return new BoardResponseDto(board, board.getUser());
    }

    public Map<String, Object> boarddelete(Long id , UserDetailsImpl userDetails) {
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("게시판이 없습니다")
        );
        User user= userDetails.getUser();
        if(Objects.equals(user.getId(), board.getUser().getId())){
            boardRepository.deleteById(id);
        }else{
            if(user.getRole() == ADMIN){
                boardRepository.deleteById(id);
            }else{
                throw new IllegalArgumentException("사용자가 작성한 댓글만 수정가능합니다");
            }
        }
        Map<String,Object> subject = new HashMap<>();
        subject.put("Success","true");
        return subject;
    }
}
