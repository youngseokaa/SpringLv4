package com.example.springlv4.entity;

import com.example.springlv4.dto.BoardRequestDto;
import com.example.springlv4.repository.BoardRepository;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
public class Board extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;


    @ManyToOne
    @JoinColumn
    private User user;

    @OneToMany(mappedBy = "board")
    private List<Comment> comments = new ArrayList<>();
    @OneToMany(mappedBy = "board")
    private List<Heart> hearts = new ArrayList<>();

    public Board(BoardRequestDto boardRequestDto, User user) {
        this.title = boardRequestDto.getTitle();
        this.content = boardRequestDto.getContent();
        this.user = user;
    }

    public Board(BoardRequestDto boardRequestDto) {
        this.title = boardRequestDto.getTitle();
        this.content = boardRequestDto.getContent();
    }

    public void update(BoardRequestDto boardRequestDto) {
        this.content = boardRequestDto.getContent();
        this.title = boardRequestDto.getTitle();
    }
}
