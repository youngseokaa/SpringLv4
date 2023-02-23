package com.example.springlv4.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    @NotBlank(message = "필수로 입력 하셔야 합니다")
    @Size(min = 4, max = 10, message =  "4자리이상 10자리 이하로 입력해 주세요")
    @Pattern(regexp = "^[a-z0-9]{4,10}*$", message = "소문자와 숫자로 이루어져야합니다")
    private String username;


}
