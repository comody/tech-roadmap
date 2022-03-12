package com.project.techroadmap.config.jwt;

import com.project.techroadmap.constant.ErrorCode;
import com.project.techroadmap.dto.*;
import com.project.techroadmap.entity.Career;
import com.project.techroadmap.entity.Member;
import com.project.techroadmap.entity.Role;
import com.project.techroadmap.exception.AccessDeniedException;
import com.project.techroadmap.exception.BusinessException;
import com.project.techroadmap.repository.CareerRepository;
import com.project.techroadmap.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class SignService {

    private final JwtTokenProvider jwtTokenProvider;
    private final MemberRepository memberRepository;
    private final CareerRepository careerRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * Dto로 들어온 값을 통해 회원가입을 진행
     * @param requestDto
     * @return
     */
    @Transactional
    public MemberWithAuthDto registerMember(MemberRegisterRequestDto requestDto) {
        validateDuplicated(requestDto.getUsername());
        Career career = careerRepository.findById(requestDto.getCareerId()).orElseThrow(() -> new BusinessException(ErrorCode.BusinessException));

        Member member = memberRepository.save(Member.builder()
                .username(requestDto.getUsername())
                .password(passwordEncoder.encode(requestDto.getPassword()))
                .age(requestDto.getAge())
                .career(career)
                .role(Role.ROLE_MEMBER)
                .build());

        String accessToken = jwtTokenProvider.createToken(member.getUsername());
        String refreshToken = jwtTokenProvider.createRefreshToken();

        return MemberWithAuthDto.builder()
                .id(member.getId())
                .username(member.getUsername())
                .age(member.getAge())
                .careerName((member.getCareer().getCareerName()))
                .role((member.getRole()))
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    /**
     * Unique한 값을 가져야하나, 중복된 값을 가질 경우를 검증
     * @param username
     */
    public void validateDuplicated(String username) {
        if (memberRepository.findByUsername(username).isPresent())
            throw new BusinessException(ErrorCode.DuplicateException);
    }

    @Transactional
    public MemberWithAuthDto loginMember(MemberLoginRequestDto requestDto) {
        Member member = memberRepository.findByUsername(requestDto.getUsername()).orElseThrow(() -> new BusinessException(ErrorCode.UsernameOrPasswordNotFoundException));
        if (!passwordEncoder.matches(requestDto.getPassword(), member.getPassword()))
            throw new BusinessException(ErrorCode.UsernameOrPasswordNotFoundException);

        String accessToken = jwtTokenProvider.createToken(member.getUsername());
        String refreshToken = jwtTokenProvider.createRefreshToken();

        return MemberWithAuthDto.builder()
                .id(member.getId())
                .username(member.getUsername())
                .age(member.getAge())
                .careerName(member.getCareer().getCareerName())
                .role(member.getRole())
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    /**
     * 토큰 재발행
     * @param requestDto
     * @return
     */
    @Transactional
    public TokenDto reIssue(TokenDto requestDto) {
        if (!jwtTokenProvider.validateToken(requestDto.getRefreshToken()))
            throw new AccessDeniedException(ErrorCode.ReLogin);

        Member member = findMemberByToken(requestDto);

        String accessToken = jwtTokenProvider.createToken(member.getUsername());
        String refreshToken = jwtTokenProvider.createRefreshToken();

        return new TokenDto(accessToken, refreshToken);
    }

    public Member findMemberByToken(TokenDto requestDto) {
        Authentication auth = jwtTokenProvider.getAuthentication(requestDto.getAccessToken());
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        String username = userDetails.getUsername();

        return memberRepository
                .findByUsername(username)
                .orElseThrow(() -> new BusinessException(ErrorCode.DuplicateException));
    }
}