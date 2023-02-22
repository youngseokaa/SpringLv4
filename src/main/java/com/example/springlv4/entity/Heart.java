package com.example.springlv4.entity;

import com.example.springlv4.dto.HeartRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Entity
@NoArgsConstructor
@Getter
public class Heart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private boolean heart;

    @ManyToOne
    @JoinColumn
    private Board board;

    @ManyToOne
    @JoinColumn
    private User user;

    @ManyToOne
    @JoinColumn
    private Comment comment;


    public Heart(HeartRequestDto heartRequestDto,Board board, User user){
        this.heart =heartRequestDto.isHeart();
        this.board = board;
        this.user = user;
    }
    public Heart(HeartRequestDto heartRequestDto,Comment comment, User user,Board board){
        this.heart =heartRequestDto.isHeart();
        this.comment = comment;
        this.user = user;
        this.board = board;
    }
}
