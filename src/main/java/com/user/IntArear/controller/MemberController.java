package com.user.IntArear.controller;

import com.user.IntArear.dto.MemberRequestDto;
import com.user.IntArear.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity<String> saveMember(@RequestBody MemberRequestDto memberRequestDto) {

        memberService.signup(memberRequestDto);

        return ResponseEntity.ok().build();
    }
}
