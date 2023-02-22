package com.example.springlv4.dto;

import com.example.springlv4.entity.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class CommentforDto {
    private Long id;
    private String comment;
    private int heartcomment;
    private String Username;
    private LocalDateTime createdAt;
    CommentforDto(Comment comment) {
        this.id = comment.getBoard().getId();
        this.comment = comment.getComment();
        this.createdAt = comment.getBoard().getCreatedAt();
        this.heartcomment = comment.getHeartss().size();
        this.Username = comment.getUser().getUsername();
    }
}
