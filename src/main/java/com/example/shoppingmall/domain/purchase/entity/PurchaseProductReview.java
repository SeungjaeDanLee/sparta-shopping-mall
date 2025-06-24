package com.example.shoppingmall.domain.purchase.entity;

import com.example.shoppingmall.domain.product.entity.Product;
import com.example.shoppingmall.domain.user.entity.User;
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

@Table(name = "purchase_product_review")
@Entity
@Getter
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PurchaseProductReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    Product product;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "purchase_product_id")
    PurchaseProduct purchaseProduct;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    User user;

    @Column(nullable = false, length = 100)
    String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    String content;

    @Column(nullable = false)
    Integer rating;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    LocalDateTime createdAt;

    @UpdateTimestamp
    @Column
    LocalDateTime updatedAt;

    @Builder
    public PurchaseProductReview(Product product, PurchaseProduct purchaseProduct, User user, String title, String content, Integer rating) {
        this.product = product;
        this.purchaseProduct = purchaseProduct;
        this.user = user;
        this.title = title;
        this.content = content;
        this.rating = rating;
    }

    public void update(String title, String content, Integer rating) {
        this.title = title;
        this.content = content;
        this.rating = rating;
    }
} 