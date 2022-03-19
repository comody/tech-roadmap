package com.project.techroadmap.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@ApiModel(value = "MemberRegisterRequestDto")
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
