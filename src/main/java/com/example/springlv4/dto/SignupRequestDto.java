package com.example.springlv4.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SignupRequestDto {

    @NotBlank(message = "필수로 입력 하셔야 합니다")
    @Size(min = 4, max = 10, message =  "4자리이상 10자리 이하로 입력해 주세요")
    @Pattern(regexp = "^[a-z0-9]{4,10}.*$", message = "소문자와 숫자로 이루어져야합니다")
    private String username;

    @NotBlank(message = "필수로 입력 하셔야 합니다")
    @Size(min = 6, max = 15, message =  "4자리이상 15자리 이하로 입력해 주세요")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[~!@#$%^&*()+|=])[A-Za-z\\d~!@#$%^&*()+|=]{6,15}.*$", message = "비밀번호는 영어, 숫자, 특수기호를 포함 시켜주세요")
    private String password;


    private boolean admin = false;
    private String adminToken = "";
}
