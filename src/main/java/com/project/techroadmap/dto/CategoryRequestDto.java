package com.project.techroadmap.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@ApiModel(value = "CategoryRequestDto")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryRequestDto {

    private String categoryName;
    private Long parentId;
}
