package com.project.techroadmap.controller;

import com.project.techroadmap.constant.ErrorCode;
import com.project.techroadmap.dto.*;

import com.project.techroadmap.entity.Category;

import com.project.techroadmap.entity.Material;
import com.project.techroadmap.exception.BusinessException;
import com.project.techroadmap.repository.MaterialRepository;
import com.project.techroadmap.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Api(value = "Category Controller", tags = "Category") // 1
@RestController
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    private final MaterialRepository materialRepository;

    @ApiOperation(value = "카테고리", notes = "카테고리를 가져온다.") // 2
    @GetMapping("/api/v1/categories")
    public ResponseDto<List<CategoryDto>> categories() {
        List<Category> findCategories = categoryService.findCategories();

        List<CategoryDto> collect = findCategories.stream()
                .map(CategoryDto::new)
                .collect(Collectors.toList());
        return new ResponseDto<>(collect);
    }

    @ApiOperation(value = "카테고리생성", notes = "카테고리를 생성한다.") // 2
    @PostMapping(value = "/api/v1/categories/new")
    public ResponseDto<CategoryDto> create (@RequestBody CategoryRequestDto requestDto) {
        Category category;
        if (requestDto.getParentId() == null) {
            category = categoryService.create(Category.builder()
                    .categoryName(requestDto.getCategoryName())
                    .build());
        } else {
            Category parentCategory = categoryService.findById(requestDto.getParentId()).orElseThrow(() -> new BusinessException(ErrorCode.BusinessException));

            category = categoryService.create(Category.builder()
                    .categoryName(requestDto.getCategoryName())
                    .parent(parentCategory)
                    .build());
        }

        return new ResponseDto<>(new CategoryDto(category));
    }

    @ApiOperation(value = "카테고리 내 작업생성", notes = "카테고리 내 작업을 생성한다.") // 2
    @PostMapping(value = "/api/v1/categories/{category_id}/materials")
    public ResponseDto<MaterialDto> create (@PathVariable(value="category_id") String id,
                                            @RequestBody MaterialRequestDto requestDto) {
        try {
            Long long_id = Long.parseLong(id);
            Category category = categoryService.findById(long_id).orElseThrow(() -> new BusinessException(ErrorCode.BusinessException));

            Material material = materialRepository.save(Material.builder()
                    .materialName(requestDto.getMaterialName())
                    .url(requestDto.getUrl())
                    .category(category)
                    .build());

            return new ResponseDto<>(new MaterialDto(material));
        } catch(NumberFormatException nfe){
            throw new BusinessException(ErrorCode.InvalidInputValueException);
        }
    }

}