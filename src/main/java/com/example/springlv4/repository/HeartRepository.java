package com.example.springlv4.repository;

import com.example.springlv4.entity.Board;
import com.example.springlv4.entity.Heart;
import org.springframework.data.geo.GeoResult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HeartRepository extends JpaRepository<Heart,Long> {
    Optional<Heart> findByUser_IdAndBoard_Id(Long id, Long id1);

}
