package com.itsol.mockup.repository;

import com.itsol.mockup.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryEntity,Long> {
    CategoryEntity getCategoryEntityByCategoryId(Long id);
}
