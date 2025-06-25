# Sparta Shopping Mall 프로젝트

**README Version: 1.0.0**  
**Last Updated: 2025-06-25**

스프링부트 기반의 쇼핑몰 백엔드 프로젝트입니다.  
상품, 주문, 결제, 배송, 리뷰, 쿠폰 등 커머스의 핵심 기능을 도메인별로 설계/구현했습니다.

---

## 프로젝트 구조

```
src/main/java/com/example/shoppingmall/
├── common/         # 공통 유틸, enum, 상수 등
│   ├── enums/
│   └── util/
├── global/         # 전역 설정, 공통 엔티티, 설정 파일 등
│   └── config/
├── domain/
│   ├── user/       # 회원, 배송지, 쿠폰 등
│   │   └── entity/
│   ├── product/    # 상품, 옵션, 이미지, QnA 등
│   │   └── entity/
│   ├── purchase/   # 주문, 주문상품, 배송, 리뷰 등
│   │   └── entity/
│   ├── payment/    # 결제 트랜잭션
│   │   └── entity/
│   ├── cart/       # 장바구니
│   │   └── entity/
│   ├── coupon/     # 쿠폰
│   │   └── entity/
│   └── refund/     # 환불
│       └── entity/
```

---

## 주요 도메인 및 엔티티

### 1. User (회원)
- **User**: 회원 정보, 권한, 상태, UUID 등
- **UserAddress**: 회원별 배송지(여러 개)
- **UserCoupon**: 회원이 보유한 쿠폰

### 2. Product (상품)
- **Product**: 상품 기본 정보
- **ProductImage**: 상품별 이미지(썸네일/상세 등)
- **ProductOptionGroup/Value**: 옵션 그룹/값(예: 색상/사이즈)
- **ProductVariant/Option**: 옵션 조합별 SKU, 옵션 매핑
- **ProductQna**: 상품 Q&A

### 3. Purchase (주문)
- **Purchase**: 주문(영수증 헤더), 쿠폰 사용/할인 금액 포함
- **PurchaseProduct**: 주문별 상품 상세
- **PurchaseShipping**: 주문별 배송 정보
- **PurchaseProductReview**: 주문상품별 리뷰

### 4. Payment (결제)
- **Payment**: 결제 트랜잭션, 결제수단/상태/PG사 거래번호 등

### 5. Cart (장바구니)
- **Cart**: 회원별 장바구니
- **CartItem**: 장바구니별 상품

### 6. Coupon (쿠폰)
- **Coupon**: 쿠폰 정보

### 7. Refund (환불)
- **Refund**: 환불 요청/처리
- **RefundItem**: 환불 상품 상세

### 8. Category (카테고리)
- **Category**: 상품 카테고리 정보

---

## 빌드 및 실행

```bash
./gradlew build
./gradlew bootRun
```

- DB 마이그레이션은 Flyway로 자동 적용
- application.yml에서 DB 접속 정보, 포트 등 환경설정

---

## 개발/운영 팁

- 엔티티별 Repository를 만들어 CRUD/조회/검색 등 확장성 확보
- 연관관계는 실무적으로 cascade, orphanRemoval 등 옵션을 적극 활용
- 도메인별 패키지 분리로 MSA 전환도 용이
- 마이그레이션 파일은 생성 순서(번호)에 주의 (외래키 참조 순서!)

---

## 테스트

- JPA 매핑 테스트는 @DataJpaTest로 엔티티별 저장/조회 테스트 작성 권장
- 인메모리 DB(H2)로 빠른 테스트 가능

---

## 기타

- 실무에서 자주 쓰이는 확장 테이블(알림, 포인트, 찜, 이벤트 등)은 필요시 추가
- 네이밍은 도메인+서브도메인 패턴(예: PurchaseShipping, PurchaseRefund 등)으로 일관성 유지

---

