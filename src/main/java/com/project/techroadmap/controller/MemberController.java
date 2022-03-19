package com.project.techroadmap.controller;

import com.project.techroadmap.config.jwt.SignService;
import com.project.techroadmap.dto.*;
import com.project.techroadmap.entity.Member;
import com.project.techroadmap.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@Api(value = "Member Controller", tags = "Member") // 1
@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    private final SignService signService;

    @ApiOperation(value = "유저 조회", notes = "유저를 전체 조회합니다")
    @GetMapping("/api/v1/users")
    public ResponseDto<List<MemberDto>> users() {
        List<Member> findMembers = memberService.findUsers();

        List<MemberDto> collect = findMembers.stream()
                .map(m -> new MemberDto(m.getId(), m.getUsername(), m.getAge(), m.getCareer().getCareerName(), m.getRole()))
                .collect(Collectors.toList());
        return new ResponseDto<>(collect);
    }

    @ApiOperation(value = "유저등록", notes = "유저등록 한다.")
    @PostMapping("/api/v1/auth/register")
    public ResponseDto<MemberWithAuthDto> register(@RequestBody MemberRegisterRequestDto requestDto) {
        MemberWithAuthDto memberWithAuth = signService.registerMember(requestDto);
        return new ResponseDto<>(memberWithAuth);
    }

    @ApiOperation(value = "로그인", notes = "로그인 한다.")
    @PostMapping("/api/v1/auth/login")
    public ResponseDto<MemberWithAuthDto> login(@RequestBody MemberLoginRequestDto requestDto) {
        MemberWithAuthDto memberWithAuth = signService.loginMember(requestDto);
        return new ResponseDto<>(memberWithAuth);
    }

    @ApiOperation(value = "토큰 재발급", notes = "리프레시 토큰으로 새로운 액세스 토큰을 발급 받는다.")
    @PostMapping("/api/v1/auth/reissue")
    public ResponseDto<TokenDto> reIssue(@RequestBody TokenDto requestDto) {
        TokenDto token = signService.reIssue(requestDto);
        return new ResponseDto<>(token);
    }

}
