package com.example.springlv4.repository;

import com.example.springlv4.entity.Board;
import com.example.springlv4.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Long> {

}
