package com.user.IntArear.controller;

import com.user.IntArear.dto.MemberRequestDto;
import com.user.IntArear.dto.MemberResponseDto;
import com.user.IntArear.jwt.TokenProvider;
import com.user.IntArear.service.MemberService;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MemberController {

    private static final Logger log = LoggerFactory.getLogger(MemberController.class);
    private final MemberService memberService;
    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody MemberRequestDto memberRequestDto) {

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(memberRequestDto.getEmail(), memberRequestDto.getPassword());

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        ResponseCookie jwtCookie = tokenProvider.createToken(authentication);

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                .build();
    }

    @PostMapping("/signup")
    public ResponseEntity<String> saveMember(@RequestBody MemberRequestDto memberRequestDto) {

        memberService.signup(memberRequestDto);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/member/info")
    public ResponseEntity<MemberResponseDto> getMemberInfo(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String role = String.valueOf(userDetails.getAuthorities().stream().findFirst()
                .orElseThrow(() -> new UsernameNotFoundException("User not found")));

        MemberResponseDto memberInfo = MemberResponseDto.builder()
                .email(authentication.getName())
                .role(role)
                .build();

        return ResponseEntity.ok().body(memberInfo);
    }
}
