package com.example.springlv4.dto;

import com.example.springlv4.entity.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor
public class CommentResponseDto {
    private Long id;
    private String title;
    private String content;
    private String Username;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private String comment;
   public CommentResponseDto(Comment comment){
       this.id = comment.getBoard().getId();
       this.title = comment.getBoard().getTitle();
       this.content = comment.getBoard().getContent();
       this.Username = comment.getUser().getUsername();
       this.createdAt = comment.getBoard().getCreatedAt();
       this.modifiedAt= comment.getBoard().getModifiedAt();
       this.comment =comment.getComment();
    }
}
