package com.itsol.mockup.services;

import com.itsol.mockup.entity.CategoryEntity;
import com.itsol.mockup.web.dto.category.CategoryDTO;
import com.itsol.mockup.web.dto.response.ArrayResultDTO;
import com.itsol.mockup.web.dto.response.BaseResultDTO;

public interface CategoryService {
    ArrayResultDTO<CategoryEntity> findAll();
    BaseResultDTO addCategory(CategoryDTO categoryDTO);
    BaseResultDTO updateCategory(CategoryDTO categoryDTO);
    BaseResultDTO deleteCategory(Long id);
}
