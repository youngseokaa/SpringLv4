package com.example.springlv4.dto;

import com.example.springlv4.entity.Heart;
import com.example.springlv4.entity.Hearts;
import lombok.Getter;

import lombok.Setter;

@Getter
@Setter

public class HeartsResponseDto {
    private boolean heart;

    public HeartsResponseDto(Hearts heart) {
        this.heart = heart.isHeart();
    }
}
