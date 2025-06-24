package com.example.shoppingmall.domain.user.entity;

import com.example.shoppingmall.common.enums.Gender;
import com.example.shoppingmall.common.enums.LoginType;
import com.example.shoppingmall.common.enums.UserRole;
import com.example.shoppingmall.common.enums.UserStatus;
import com.example.shoppingmall.domain.cart.entity.Cart;
import com.example.shoppingmall.domain.purchase.entity.Purchase;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Table(name = "user")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "BINARY(16)", nullable = false, updatable = false, unique = true)
    private UUID uuid;

    @Column(length = 20, unique = true)
    private String customerNumber;

    @Column(nullable = false, length = 20)
    private String name;

    @Column(nullable = false, unique = true, length = 50)
    private String email;

    @Column(nullable = false, length = 255)
    private String passwordHash;

    @Column(nullable = false, length = 20)
    private String phone;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private LocalDate birthDate;

    @Column(length = 500)
    private String profileImageUrl;

    @Enumerated(EnumType.STRING)
    @ColumnDefault("'EMAIL'")
    private LoginType loginType = LoginType.EMAIL;

    @Enumerated(EnumType.STRING)
    @ColumnDefault("'ACTIVE'")
    private UserStatus status = UserStatus.ACTIVE;

    @Enumerated(EnumType.STRING)
    @ColumnDefault("'USER'")
    private UserRole role = UserRole.USER;

    private LocalDateTime lastLoginAt;

    @Column(nullable = false)
    private LocalDateTime termsAgreedAt;

    @Column(nullable = false)
    private LocalDateTime privacyAgreedAt;

    private LocalDateTime marketingAgreedAt;

    @ColumnDefault("false")
    private Boolean isEmailVerified = false;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    // 연관관계 매핑
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Cart> carts = new ArrayList<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Purchase> purchases = new ArrayList<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    List<UserCoupon> userCoupons = new ArrayList<>();

    // 역할 확인 메서드들
    public boolean isUser() {
        return this.role == UserRole.USER;
    }

    public boolean isProductAdmin() {
        return this.role == UserRole.PRODUCT_ADMIN;
    }

    public boolean isSuperAdmin() {
        return this.role == UserRole.SUPER_ADMIN;
    }

    public boolean hasAdminRole() {
        return this.role == UserRole.PRODUCT_ADMIN || this.role == UserRole.SUPER_ADMIN;
    }

    @PrePersist
    public void assignUuid() {
        if (this.uuid == null) {
            this.uuid = UUID.randomUUID();
        }
    }
} 