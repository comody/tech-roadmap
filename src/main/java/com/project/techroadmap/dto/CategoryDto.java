package com.project.techroadmap.dto;

import com.project.techroadmap.entity.Category;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ApiModel(value = "CategoryDto")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {
    private Long id;

    private String categoryName;

    private List<CategoryDto> children;

    private List<MaterialDto> materials = new ArrayList<MaterialDto>();

    public CategoryDto (Category category) {
        this.id = category.getId();
        this.categoryName = category.getCategoryName();
        this.materials = category.getMaterials().stream().map(MaterialDto::new).collect(Collectors.toList());
        this.children = category.getChildren().stream().map(CategoryDto::new).collect(Collectors.toList());
    }

}
