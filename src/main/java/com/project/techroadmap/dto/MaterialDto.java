package com.project.techroadmap.dto;

import com.project.techroadmap.entity.Material;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
