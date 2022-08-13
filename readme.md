# 🥕 당근 마켓 클론 코딩 - BAuction

MAU 1800만의 중고 거래 플랫폼 API를 재현해보기 위해 시작한 사이드 프로젝트입니다.

차별점으로 기존의 동네 단위의 서비스를 대학교 단위의 서비스로 바꿔보았습니다.

## Initialization

### clone repository

```bash
$ git clone git@github.com:h-jjang/bauction.git
```

### Docker build up

```bash
# /baution
$ docker-compose up --build
```

## Project Goal

1. 당근 마켓의 MVP라 생각되는 부분에 대해서 서비스를 직접 분석하여 유사하게 구현
2. 대용량 트래픽에도 장애없이 동작할 수 있는 코드, 가독성이 좋고 유지보수 쉬운 코드에 대해 고민
3. 아래 4가지 항목 구현

### Spring Boot

- 객체지향적 설계
    - SOLID
    - 의존성 주입 (Dependency Injection)
- Thread-Safe

### DB + JPA

- N + 1 Issue (LAZY Loading, Fetch Join)
- Transaction

### JWT + Security + OAuth

- 소셜 로그인

### STOMP, SMTP, Async

- 실시간 채팅
- 메일 비동기 전송

## Wire Frame

![Wire Frame](https://user-images.githubusercontent.com/73998876/184500678-17a20d69-9772-4a5c-ab7e-cbebdbfdeb52.png)

## Architecture

![Architecture](https://user-images.githubusercontent.com/73998876/184500716-8cbe4d0a-0e3d-4127-b957-de366cf4196b.png)

Backend

- Java 11
- Gradle 7.4.1
- Spring Boot 2.6.4
- Spring Security + OAuth 2.0
- Spring Data JPA + MySQL 5.7
- Log4j 2
- JUnit 5, Mockito, Jacoco, H2
- Swagger, Spring REST Docs

Frontend

- React.js

ETC

- Kakao Login
- Gmail
- AWS S3
- Docker
- NGINX

## Process


### 상품 거래

[등록]

1. 카테고리 등록
2. 게시글 등록 + 이미지 업로드
    - 메일 알림 전송
    - 거래 상태 등록 (대기)


[검색]

1. 전체 게시글 검색
2. 키워드 검색
3. 키워드 + 필터 검색


[구매]

대화 → 예약 → 구매확정

1. 대화 - STOMP 방식을 통한 실시간 채팅
2. 예약 - 거래 상태 변경 (예약)
    - 메일 알림 전송
3. 구매 확정 - 거래 상태 변경 (승인)
    - 메일 알림 전송


[삭제]

1. 게시글 삭제
2. 이미지 삭제
3. 거래 상태 삭제


### 회원

[소셜 로그인]

1. 카카오 로그인
    - 유저 토큰 발급

[대학교 인증]

1. 메일 전송
2. 인증 코드 입력

## Members

| 이름       | 개발분야                           | 담당                                         | 소개페이지                                         |
| ---------- | ---------------------------------- | -------------------------------------------- | -------------------------------------------------- |
| 김영준   | BE, Team Lead | 게시글, 거래, 이미지 업로드 API, DB Design | [개인 리포로 이동](https://github.com/orgs/h-jjang/people/0BVer)  |
| 김기현   | BE          | Security, OAuth, Chatting API, DB Design                      | [개인 리포로 이동](https://github.com/orgs/h-jjang/people/kim1387)   |
| 조윤근   | BE, DevOps  | 검색, 메일 인증 API, Deploy, DB Design                   | [개인 리포로 이동](https://github.com/orgs/h-jjang/people/Yunkeun)   |
| 박근우   | BE            | 카페고리 API, DB Design                  | [개인 리포로 이동](https://github.com/Gnu-Kenny) |


# Tech Blog

[BAuction 프로젝트 회고](https://medium.com/@dr0joon/bauction-%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8-%ED%9A%8C%EA%B3%A0-d4b8711409c8)

[SMTP와 비동기](https://medium.com/@choyun0415/spring-smtp%EC%99%80-%EB%B9%84%EB%8F%99%EA%B8%B0-b567125433e3)

[채팅 API 구현에 WebSocket + STOMP 도입 이유](https://medium.com/@qkr0677/%EC%B1%84%ED%8C%85-api-%EA%B5%AC%ED%98%84%EC%97%90-websocket-stomp-%EB%8F%84%EC%9E%85-%EC%9D%B4%EC%9C%A0-d3ed17776441)
