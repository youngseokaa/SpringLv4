package com.example.springlv4.entity;

import com.example.springlv4.dto.CommentRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String comment;

    @ManyToOne
    @JoinColumn(name="BOARD_ID",nullable = false)
    private Board board;


    @OneToMany(mappedBy = "comment")
    private List<Heart> hearts = new ArrayList<>();

    @OneToMany(mappedBy = "comment")
    private List<Hearts> heartss = new ArrayList<>();

    @ManyToOne
    @JoinColumn
    private User user;

    public Comment(CommentRequestDto commentRequestDto,Board board,User user){
        this.comment = commentRequestDto.getComment();
        this.board = board;
        this.user = user;
    }

    public void update(CommentRequestDto commentRequestDto) {
        this.comment = commentRequestDto.getComment();
    }
}
