package com.project.techroadmap.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MaterialRequestDto {
    private String materialName;
    private String url;

    private Long categoryId;
}
