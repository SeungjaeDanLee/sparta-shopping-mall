package com.example.shoppingmall.domain.user.repository;

import com.example.shoppingmall.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
} 