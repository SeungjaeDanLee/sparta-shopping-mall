package com.example.shoppingmall.domain.purchase.entity;

import com.example.shoppingmall.domain.product.entity.ProductVariant;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Table(name = "purchase_product")
@Entity
@Getter
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PurchaseProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "purchase_id")
    Purchase purchase;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_variant_id")
    ProductVariant productVariant;

    @Column(nullable = false, columnDefinition = "int default 1")
    Integer quantity;

    @Column(nullable = false, precision = 19, scale = 2)
    BigDecimal priceAtPurchase;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    LocalDateTime createdAt;

    @Builder
    public PurchaseProduct(
            Purchase purchase,
            ProductVariant productVariant,
            Integer quantity,
            BigDecimal priceAtPurchase
    ) {
        this.purchase = purchase;
        this.productVariant = productVariant;
        this.quantity = quantity;
        this.priceAtPurchase = priceAtPurchase;
    }
} 