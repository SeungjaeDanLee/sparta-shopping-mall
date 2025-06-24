package com.example.shoppingmall.domain.user.entity;

import com.example.shoppingmall.domain.coupon.entity.Coupon;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;

@Table(name = "user_coupon")
@Entity
@Getter
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCoupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coupon_id")
    Coupon coupon;

    @Column(nullable = false)
    @ColumnDefault("false")
    Boolean isUsed;

    @Column
    LocalDateTime usedAt;

    @Column(nullable = false)
    LocalDateTime expiresAt;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    LocalDateTime createdAt;

    @Builder
    public UserCoupon(User user, Coupon coupon, LocalDateTime expiresAt) {
        this.user = user;
        this.coupon = coupon;
        this.expiresAt = expiresAt;
        this.isUsed = false;
    }

    public void use() {
        if (Boolean.TRUE.equals(this.isUsed)) {
            // TODO: 이미 사용된 쿠폰 예외 처리
            throw new IllegalStateException("이미 사용된 쿠폰입니다.");
        }
        this.isUsed = true;
        this.usedAt = LocalDateTime.now();
    }
} 