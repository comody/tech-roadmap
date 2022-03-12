package com.project.techroadmap.service;

import com.project.techroadmap.entity.Category;
import com.project.techroadmap.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class CategoryService {

    @Autowired
    CategoryRepository careerRepository;

    @Transactional
    public Category create(Category category) {
        if (category.getParent() != null) {
            validateParentCategory(category);
        }

        careerRepository.save(category);

        return category;
    }

    private void validateParentCategory(Category category) {
        Optional<Category> findCategory =
                careerRepository.findById(category.getParent().getId());
        if (findCategory.isEmpty()) {
            throw new IllegalStateException("parent 카테고리가 존재하지 않습니다.");
        }
    }

    public Optional<Category> findById(Long id) {
        return careerRepository.findById(id);
    }

    public List<Category> findCategories() {
        return careerRepository.findAllByParentIsNull();
    }

}