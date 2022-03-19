package com.project.techroadmap.dto;

import com.project.techroadmap.entity.Role;
import io.swagger.annotations.ApiModel;
import lombok.*;

@ApiModel(value = "MemberDto")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberDto {

    private Long id;
    private String username;
    private int age;
    private String careerName;

    private Role role = Role.ROLE_MEMBER;
}
