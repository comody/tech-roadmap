package com.project.techroadmap.dto;

import com.project.techroadmap.entity.Role;
import io.swagger.annotations.ApiModel;
import lombok.*;

@ApiModel(value = "MemberWithAuthDto")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberWithAuthDto {

    private Long id;
    private String username;
    private int age;
    private String careerName;

    private Role role = Role.ROLE_MEMBER;
    private String accessToken;
    private String refreshToken;
}
