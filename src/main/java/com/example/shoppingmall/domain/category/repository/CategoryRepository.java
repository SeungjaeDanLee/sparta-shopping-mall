package com.example.shoppingmall.domain.category.repository;

import com.example.shoppingmall.domain.category.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByName(String cloth);
}