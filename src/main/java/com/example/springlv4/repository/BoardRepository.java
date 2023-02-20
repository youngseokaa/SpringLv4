package com.example.springlv4.repository;

import com.example.springlv4.entity.Board;
import com.example.springlv4.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board,Long> {
}
