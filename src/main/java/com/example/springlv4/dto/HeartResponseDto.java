package com.example.springlv4.dto;

import com.example.springlv4.entity.Heart;
import com.example.springlv4.entity.Hearts;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HeartResponseDto {
    private boolean heart;

    public HeartResponseDto(Heart heart){
        this.heart = heart.isHeart();
    }

}


