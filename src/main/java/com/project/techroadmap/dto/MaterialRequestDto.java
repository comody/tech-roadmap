package com.project.techroadmap.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@ApiModel(value = "MaterialRequestDto")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MaterialRequestDto {
    private String materialName;
    private String url;
}
