package com.example.springlv4.entity;

import com.example.springlv4.dto.HeartRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "heartsr")
@NoArgsConstructor
@Getter
public class Hearts {
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

    public Hearts(HeartRequestDto heartRequestDto, Comment comment, User user, Board board) {
        this.heart = heartRequestDto.isHeart();
        this.comment = comment;
        this.user = user;
        this.board = board;
    }
}
