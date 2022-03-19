package com.project.techroadmap.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@ApiModel(value = "CareerRequestDto")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CareerRequestDto {

    @ApiModelProperty(value = "직업", notes = "사용자의 직업을 입력해주세요", required = true, example = "백앤드개발자")
    private String careerName;
}
