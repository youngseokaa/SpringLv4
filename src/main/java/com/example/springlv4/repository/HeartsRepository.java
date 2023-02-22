package com.example.springlv4.repository;

import com.example.springlv4.entity.Comment;
import com.example.springlv4.entity.Heart;
import com.example.springlv4.entity.Hearts;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HeartsRepository extends JpaRepository<Hearts,Long> {
    Optional<Hearts> findByUser_IdAndComment_Id(Long id, Long id1);
}
