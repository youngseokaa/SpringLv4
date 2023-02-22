package com.example.springlv4.controller;

import com.example.springlv4.dto.*;
import com.example.springlv4.security.UserDetailsImpl;
import com.example.springlv4.service.BoardService;
import com.example.springlv4.service.HeartService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class HeartController {

    private final HeartService heartService;

    @PostMapping("/boardheart/{id}")
    public HeartResponseDto heart(@RequestBody HeartRequestDto heartRequestDto, @PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return heartService.heart(heartRequestDto, id, userDetails);
    }

    @PostMapping("/commentheart/{id}")
    public HeartsResponseDto hearts(@RequestBody HeartRequestDto heartRequestDto, @PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return heartService.hearts(heartRequestDto, id, userDetails);
    }
}
