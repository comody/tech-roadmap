package com.project.techroadmap.controller;

import com.project.techroadmap.config.jwt.SignService;
import com.project.techroadmap.dto.*;
import com.project.techroadmap.entity.Member;
import com.project.techroadmap.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    private final SignService signService;

    @GetMapping("/api/v1/users")
    public ResponseDto<List<MemberDto>> users() {
        List<Member> findMembers = memberService.findUsers();

        List<MemberDto> collect = findMembers.stream()
                .map(m -> new MemberDto(m.getId(), m.getUsername(), m.getAge(), m.getCareer().getCareerName(), m.getRole()))
                .collect(Collectors.toList());
        return new ResponseDto<>(collect);
    }

    @PostMapping("/api/v1/register")
    public ResponseDto<MemberWithAuthDto> register(@RequestBody MemberRegisterRequestDto requestDto) {
        MemberWithAuthDto memberWithAuth = signService.registerMember(requestDto);
        return new ResponseDto<>(memberWithAuth);
    }

    @PostMapping("/api/v1/login")
    public ResponseDto<MemberWithAuthDto> login(@RequestBody MemberLoginRequestDto requestDto) {
        MemberWithAuthDto memberWithAuth = signService.loginMember(requestDto);
        return new ResponseDto<>(memberWithAuth);
    }

    @PostMapping("/api/v1/reissue")
    public ResponseDto<TokenDto> reIssue(@RequestBody TokenDto requestDto) {
        TokenDto token = signService.reIssue(requestDto);
        return new ResponseDto<>(token);
    }

}
