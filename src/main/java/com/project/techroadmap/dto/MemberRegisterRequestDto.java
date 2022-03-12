package com.project.techroadmap.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberRegisterRequestDto {
    private int age;
    private String username;
    private Long careerId;
    private String password;
}
