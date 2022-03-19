package com.project.techroadmap.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@ApiModel(value = "MemberLoginRequestDto")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberLoginRequestDto {
    private String username;
    private String password;
}
