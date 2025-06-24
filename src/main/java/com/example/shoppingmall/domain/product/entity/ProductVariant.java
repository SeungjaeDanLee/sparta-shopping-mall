package com.example.shoppingmall.domain.product.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Table(name = "product_variant")
@Entity
@Getter
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductVariant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    Product product;

    @Column(precision = 19, scale = 2)
    BigDecimal price;

    @Column
    Integer stock;

    @OneToMany(mappedBy = "productVariant", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    List<ProductVariantOption> options = new ArrayList<>();

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    LocalDateTime createdAt;

    @UpdateTimestamp
    @Column
    LocalDateTime updatedAt;

    @Builder
    public ProductVariant(Product product, BigDecimal price, Integer stock) {
        this.product = product;
        this.price = price;
        this.stock = stock;
    }

    public void update(BigDecimal price, Integer stock) {
        this.price = price;
        this.stock = stock;
    }

    public void addStock(Integer quantity) {
        this.stock += quantity;
    }

    public void removeStock(Integer quantity) {
        int restStock = this.stock - quantity;
        if (restStock < 0) {
            // TODO: 재고 부족 예외 처리
            throw new IllegalArgumentException("재고가 부족합니다.");
        }
        this.stock = restStock;
    }
} 