package com.example.springlv4.dto;

import com.example.springlv4.entity.Board;
import com.example.springlv4.entity.Comment;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
public class BoardHeartResponseDto {
    private Long id;
    private String title;
    private String content;
    private String Username;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private int heart;
    private List<CommentforDto> commentlists;
    public BoardHeartResponseDto(Board board){
        List <CommentforDto> commentlists = new ArrayList<>();
        Collections.reverse( board.getComments());
        for (Comment comment : board.getComments()) {
            commentlists.add(new CommentforDto(comment));
        }
        this.commentlists =commentlists;
        this.id = board.getId();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.Username = board.getUser().getUsername();
        this.createdAt = board.getCreatedAt();
        this.modifiedAt = board.getModifiedAt();
        this.heart = board.getHearts().size();

}
}
