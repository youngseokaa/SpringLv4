package com.example.springlv4.dto;

import com.example.springlv4.entity.Board;
import com.example.springlv4.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class BoardResponseDto {
    private Long id;
    private String title;
    private String content;
    private String Username;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    public BoardResponseDto(Board board, User user) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.Username = user.getUsername();
        this.createdAt = board.getCreatedAt();
        this.modifiedAt = board.getModifiedAt();
    }

}