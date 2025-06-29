package com.example.shoppingmall.domain.product.repository;

import com.example.shoppingmall.domain.category.entity.Category;
import com.example.shoppingmall.domain.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByCategory(Category category);
} 