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

import java.time.LocalDateTime;

@Table(name = "product_image")
@Entity
@Getter
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    Product product;

    @Column(nullable = false, length = 500)
    String url;

    @Column(nullable = false, length = 20)
    String type; // THUMBNAIL, DETAIL 등

    @Column(nullable = false)
    Integer sortOrder = 0;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    LocalDateTime createdAt;

    @UpdateTimestamp
    @Column
    LocalDateTime updatedAt;

    @Builder
    public ProductImage(Product product, String url, String type, Integer sortOrder) {
        this.product = product;
        this.url = url;
        this.type = type;
        this.sortOrder = sortOrder != null ? sortOrder : 0;
    }

    public void update(String url, String type, Integer sortOrder) {
        this.url = url;
        this.type = type;
        this.sortOrder = sortOrder != null ? sortOrder : 0;
    }
} 