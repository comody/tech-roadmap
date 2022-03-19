package com.project.techroadmap.dto;

import io.swagger.annotations.ApiModel;
import lombok.*;

@ApiModel(value = "CareerDto")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CareerDto {

    private Long id;
    private String careerName;
}
