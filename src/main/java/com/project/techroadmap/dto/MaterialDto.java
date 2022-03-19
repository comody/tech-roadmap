package com.project.techroadmap.dto;

import com.project.techroadmap.entity.Material;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@ApiModel(value = "MaterialDto")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MaterialDto {
    private Long id;

    private String materialName;
    private String url;

    private Long categoryId;

    public MaterialDto (Material material) {
        this.id = material.getId();
        this.materialName = material.getMaterialName();
        this.url =material.getUrl();
        this.categoryId = material.getCategory().getId();
    }

}
