package com.user.IntArear.controller;

import com.user.IntArear.dto.MemberRequestDto;
import com.user.IntArear.entity.Member;
import com.user.IntArear.entity.Role;
import com.user.IntArear.security.jwt.JwtUtil;
import com.user.IntArear.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class LoginController {

    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;
    private final MemberService memberService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody MemberRequestDto memberRequestDto) {

        Member member = Member.builder()
                .email(memberRequestDto.getEmail())
                .password(bCryptPasswordEncoder.encode(
                        memberRequestDto.getPassword()))
                .role(Role.ROLE_USER)
                .build();

        Member savedMember = memberService.save(member);

        if (savedMember == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
        }

        return ResponseEntity.status(HttpStatus.OK).body("Created");

    }
}
