<h1 align="center">
  생태 정보 서비스 API 개발
</h1>

대한민국 지역기반 생태 관광 정보 서비스를 개발하려고 합니다. 

## 개발 프레임워크 및 기술

* Spring Boot
* jjwt – 0.9.0
* Spring Security
* Spring JPA
* Lombok
* MySQL
* Swagger

## 문제해결 전략

JWT (JSON Web Token)는 당사자간에 정보를 안전하게 JSON 객체로 전송하기위한 간결하고 독립적 인 방법을 정의합니다.
TDD 를 활용하여, 단위 테스트 및 통합 테스트를 진행하고,
최종 API 테스트는 Swagger(~/swagger-ui.html) 를 통해서 진행합니다.
4, 5 번 API 는 GenericController 를 사용하여 만들었습니다.

### 1. 회원관리
    .
    └── api
         └── auth
              ├── v1/signup
              ├── v1/signin
              └── v1/refreshToken

1. **`/api/auth/v1/signup`**: POST. 회원 가입.
1. **`/api/auth/v1/signin`**: POST. 토큰 생성.
1. **`/api/auth/v1/refreshToken`**: POST. 토큰 갱신.

### 2. 기초코드 관리
    .
    └── api
         └── admin
              ├── v1/region
              ├── v1/region/create
              ├── v1/program
              └── v1/program/create

1. **`/api/admin/v1/region/{id}`**: GET. 서비스 지역 조회.
1. **`/api/admin/v1/region/create`**: POST. 서비스 지역 등록.
1. **`/api/admin/v1/region/{id}`**: PUT. 서비스 지역 수정.
1. **`/api/admin/v1/region/{id}`**: DELETE. 서비스 지역 삭제.
1. **`/api/admin/v1/program/{id}`**: GET. 생태 관광 정보 조회.
1. **`/api/admin/v1/program/create`**: POST. 생태 관광 정보 등록.
1. **`/api/admin/v1/program/{id}`**: PUT. 생태 관광 정보 수정.
1. **`/api/admin/v1/program/{id}`**: DELETE. 생태 관광 정보 삭제.

### 3. 생태 정보 서비스
    .
    └── api
         └── touristInfo
              ├── v1/retrieveByRegion
              ├── v1/frequency
              ├── v1/retrieveByKeyword
              └── v1/recommend

1. **`/api/touristInfo/v1/retrieveByRegion`**: POST. 생태 관광정보 조회 By 서비스 지역.
1. **`/api/touristInfo/v1/frequency`**: POST. 생태 관광정보 조회 By 프로그램 상세정보.
1. **`/api/touristInfo/v1/retrieveByKeyword`**: POST. 생태 관광정보 조회 By 프로그램 소개.
1. **`/api/touristInfo/v1/recommend`**: POST. 생태 관광정보 조회 By 추천.

## 빌드 및 실행 방법
1. Mysql 설치
   - Mysql 설치(인메모리DB 테스트후, 테스트 DB생성)
   - application.properties 에서 mysql 정보 등록.
2. Maven 설치
3. Maven 빌드 및 실행
   - mvn clean package
   - java -jar ./target/SpringBootMission-0.0.1.jar
