package com.example.springlv4.service;

import com.example.springlv4.dto.LoginRequestDto;
import com.example.springlv4.dto.LoginResponseDto;
import com.example.springlv4.dto.SignupRequestDto;
import com.example.springlv4.dto.SignupResponseDto;
import com.example.springlv4.entity.User;
import com.example.springlv4.entity.UserRoleEnum;
import com.example.springlv4.jwt.JwtUtil;
import com.example.springlv4.repository.UserRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
@Getter
public class UserService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    private static final String ADMIN_TOKEN = "AAABnvxRVklrnYxKZ0aHgTBcXukeZygoC";

    @Transactional
    public SignupResponseDto signup(SignupRequestDto signupRequestDto){
        String username = signupRequestDto.getUsername();
        String password = signupRequestDto.getPassword();
        String pattern  = "^[a-z0-9]*$";
        String pattern2  = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[~!@#$%^&*()+|=])[A-Za-z\\d~!@#$%^&*()+|=]*$";
        if(username.length() > 3 && username.length() < 11){
            if(password.length() > 7 && password.length() < 16){
                if (Pattern.matches(pattern, username) && Pattern.matches(pattern2, password)) {
                    Optional<User> found = userRepository.findByUsername(username);
                    if (found.isPresent()) {
                        throw new IllegalArgumentException("중복된 사용자가 존재합니다.");
                    }
                    UserRoleEnum role = UserRoleEnum.USER;
                    if (signupRequestDto.isAdmin()) {
                        if (!signupRequestDto.getAdminToken().equals(ADMIN_TOKEN)) {
                            throw new IllegalArgumentException("관리자 암호가 틀려 등록이 불가능합니다.");
                        }
                        role = UserRoleEnum.ADMIN;
                    }
                    User user = new User(username, password, role);
                    userRepository.save(user);
                    return new SignupResponseDto();
                } else {
                    throw new IllegalArgumentException("아이디는 소문자, 숫자만 입력해 주시고 비밀번호는 영어, 숫자만 입력해주세요");
                }
            }else{
                throw new IllegalArgumentException("비밀번호를 8자리이상 15자리 이하로 등록해주세요");
            }
        }else{
            throw new IllegalArgumentException("아이디를 4자리 이상 10자리 이하로 등록해 주세요");
        }

    }

    public LoginResponseDto login(LoginRequestDto loginRequestDto, HttpServletResponse response) {
        String username = loginRequestDto.getUsername();
        String password = loginRequestDto.getPassword();

        User user =userRepository.findByUsername(username).orElseThrow(
                ()-> new IllegalArgumentException("등록된 사용자가 없습니다.")
        );
        if(!user.getPassword().equals(password)){
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다");
        }
        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(user.getUsername(),user.getRole()));
        return new LoginResponseDto();
    }
}

