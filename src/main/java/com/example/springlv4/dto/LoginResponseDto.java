package com.example.springlv4.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponseDto {
    private String msg = "회원가입 성공";
    private int statusCode = 200;
}
