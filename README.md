# 🧑‍💻 AjouFinder
대학교 내 분실물을 효율적으로 찾아주는 웹앱 서비스
![image-removebg-preview](https://github.com/user-attachments/assets/358c29e8-cae5-47c2-a9a6-4ff033566c8e)![1-removebg-preview (1)](https://github.com/user-attachments/assets/4b3af381-5d98-426d-8a53-044159a86889)

## 🎯 개요

이 웹 애플리케이션은 [대학교 이름] 내에서 **분실물**과 **발견물**을 효율적으로 찾을 수 있도록 돕는 서비스입니다. 사용자는 분실된 물품과 발견된 물품을 게시하고, 위치 정보와 함께 관련된 세부 사항을 관리할 수 있습니다.

---

## ⚙️ 기술 스택

- **백엔드**:  ![Java](https://img.shields.io/badge/Java-007396?style=flat-square&logo=Java&logoColor=white),  ![Spring Boot](https://img.shields.io/badge/SpringBoot-6DB33F?style=flat-square&logo=SpringBoot&logoColor=white),  ![Spring Security](https://img.shields.io/badge/SpringSecurity-6DB33F?style=flat-square&logo=SpringSecurity&logoColor=white)
- **데이터베이스**:  ![MySQL](https://img.shields.io/badge/MySQL-4479A1?style=flat-square&logo=MySQL&logoColor=white)
- **캐싱**:  ![Redis](https://img.shields.io/badge/Redis-FF4438?style=flat-square&logo=Redis&logoColor=white)
- **인증**:  ![JWT](https://img.shields.io/badge/JWT-000000?style=flat-square&logo=JSONWebTokens&logoColor=white)를 사용한 사용자 로그인 및 세션 관리
- **컨테이너화**:  ![Docker](https://img.shields.io/badge/Docker-2496ED?style=flat-square&logo=Docker&logoColor=white)를 사용한 애플리케이션 배포
- **API 문서화**: ![OpenAPI](https://img.shields.io/badge/OpenAPI-0D4C92?style=flat-square&logo=OpenAPI&logoColor=white) (Swagger)

---

## 🚀 주요 기능

- **사용자 등록 및 로그인**: 이메일과 비밀번호로 사용자를 등록하고 로그인할 수 있습니다.
- **분실물 및 발견물 게시판**: 분실물과 발견물을 게시하고 검색할 수 있습니다.
- **위치 관리**: 캠퍼스 내 위치를 등록하고, 해당 위치에서 발생한 분실물/발견물 정보를 관리합니다.
- **물품 필터링**: 날짜, 위치, 상태 등 다양한 기준으로 물품을 필터링할 수 있습니다.
- **이메일 인증**: 이메일 인증을 통해 사용자 신뢰성을 보장합니다.

---

## 🛠️ API 엔드포인트

### 1. 사용자 컨트롤러

- **POST /register**  
  새 사용자를 등록합니다. (이메일, 비밀번호, 닉네임 등)
  
- **POST /login**  
  이메일과 비밀번호로 로그인합니다.

### 2. 위치 컨트롤러

- **GET /api/v1/locations**  
  등록된 모든 위치 목록을 조회합니다.
  
- **POST /api/v1/locations**  
  새로운 위치를 등록합니다.
  
- **PATCH /api/v1/locations/{locationId}/update**  
  기존 위치 정보를 업데이트합니다.

- **PATCH /api/v1/locations/{locationId}/activate**  
  위치를 활성화하여 분실물 및 발견물을 등록할 수 있게 합니다.

- **DELETE /api/v1/locations/{locationId}**  
  위치를 삭제합니다.

### 3. 게시판 컨트롤러

- **GET /api/v1/boards/lost**  
  분실물 목록을 조회합니다. 다양한 필터링 옵션을 제공합니다.

- **POST /api/v1/boards/lost**  
  새로운 분실물 게시물을 등록합니다.

- **GET /api/v1/boards/found**  
  발견물 목록을 조회합니다.

- **POST /api/v1/boards/found**  
  새로운 발견물 게시물을 등록합니다.

- **GET /api/v1/boards/{boardId}**  
  특정 게시물의 상세 정보를 조회합니다.

- **PATCH /api/v1/boards/{boardId}/resolve**  
  게시물 상태를 해결 완료로 업데이트합니다.

- **PATCH /api/v1/boards/{boardId}/process**  
  게시물 상태를 처리 중으로 업데이트합니다.

- **DELETE /api/v1/boards/{boardId}**  
  게시물을 삭제합니다.

### 4. 인증 컨트롤러

- **POST /api/v1/auth/email/verify**  
  사용자의 이메일 인증을 수행합니다.

- **GET /api/v1/auth/nickname/check**  
  닉네임의 사용 가능 여부를 확인합니다.

### 5. 게시판 필터링

- **GET /api/v1/boards/lost/filter**  
  분실물 게시물에 대해 날짜, 시간, 위치, 상태 등을 기준으로 필터링하여 조회합니다.

- **GET /api/v1/boards/found/filter**  
  발견물 게시물에 대해 날짜, 시간, 위치, 상태 등을 기준으로 필터링하여 조회합니다.

---

## 📝 Notion 링크

요구사항 명세서, API 문서는 [Notion](https://mysterious-angle-c99.notion.site/AjouFinder-1290e645957c801cbdc7ccde5759a442?pvs=74)에서 확인하실 수 있습니다.

---

---
