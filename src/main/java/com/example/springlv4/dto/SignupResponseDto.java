package com.example.springlv4.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SignupResponseDto {
    private String msg = "회원가입 성공";
    private int statusCode = 200;
}
