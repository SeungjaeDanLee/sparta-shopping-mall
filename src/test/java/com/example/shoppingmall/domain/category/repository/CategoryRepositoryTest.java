package com.example.shoppingmall.domain.category.repository;

import com.example.shoppingmall.domain.category.entity.Category;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CategoryRepositoryTest {

    @Autowired
    CategoryRepository categoryRepository;

    @Test
    @DisplayName("의류 카테고리 저장 및 조회")
    void saveCategory() {

        Category parent = categoryRepository.findById(1L).orElseThrow();
        // given
        Category category = Category.builder()
                .name("바지")
                .parent(parent)
                .build();

        // when
        Category saved = categoryRepository.save(category);

        // then
        assertThat(saved.getParent().getId()).isEqualTo(1L);
        assertThat(saved.getName()).isEqualTo("바지");
    }
} 