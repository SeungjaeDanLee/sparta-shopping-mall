package com.example.shoppingmall.domain.user.repository;

import com.example.shoppingmall.common.enums.Gender;
import com.example.shoppingmall.common.enums.LoginType;
import com.example.shoppingmall.common.enums.UserRole;
import com.example.shoppingmall.common.enums.UserStatus;
import com.example.shoppingmall.domain.user.entity.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

// 서비스/비즈니스 로직, 통합 플로우 검증이면 @SpringBootTest
@SpringBootTest
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    void 성공() {
        System.out.println("연결 성공");
    }

    @Test
    @DisplayName("9명의 유저 데이터 저장 및 조회")
    void saveNineUsers() {
        // given
        List<User> users = IntStream.rangeClosed(1, 9)
                .mapToObj(i -> User.builder()
                        .name("유저" + i)
                        .email("user" + i + "@test.com")
                        .passwordHash("pw" + i)
                        .phone("010-1234-567" + i)
                        .gender(Gender.MALE)
                        .birthDate(LocalDate.of(1990, 1, 1))
                        .profileImageUrl("https://example.com/profile" + i + ".jpg")
                        .loginType(LoginType.EMAIL)
                        .status(UserStatus.ACTIVE)
                        .role(UserRole.USER)
                        .termsAgreedAt(LocalDateTime.now())
                        .privacyAgreedAt(LocalDateTime.now())
                        .marketingAgreedAt(LocalDateTime.now())
                        .build())
                .toList();

        // when
        userRepository.saveAll(users);

        // then
        List<User> all = userRepository.findAll();
        assertThat(all).hasSize(9);
    }
} 