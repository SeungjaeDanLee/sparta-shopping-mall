package com.example.shoppingmall.domain.product.repository;

import com.example.shoppingmall.domain.category.entity.Category;
import com.example.shoppingmall.domain.category.repository.CategoryRepository;
import com.example.shoppingmall.domain.product.entity.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.IntStream;

@SpringBootTest
class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryRepository categoryRepository;

    @Test
    @DisplayName("의류 카테고리에 상품 10개 저장 및 조회")
    void saveTenProducts() {
        // given
        String cloth = "바지";
        Category category = categoryRepository.findByName(cloth);
        List<Product> products = IntStream.rangeClosed(1, 10)
                .mapToObj(i -> Product.builder()
                        .category(category)
                        .name("바지상품" + i)
                        .description("바지 상품 설명 " + i)
                        .build())
                .toList();

        // when
        productRepository.saveAll(products);
    }

    @Test
    void findAll() {
        List<Product> products = productRepository.findAll();
        for (Product product : products) {
            System.out.println("상품명 : " + product.getName());
        }
    }

    @Test
    void findAllByCategory() {
        String categoryName = "바지";
        Category category = categoryRepository.findByName(categoryName);
        List<Product> products = productRepository.findAllByCategory(category);
        for (Product product : products) {
            System.out.println("상품명 : " + product.getName());
        }
    }
} 