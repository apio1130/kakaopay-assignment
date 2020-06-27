# 카카오페이지 과제

### 카카오페이 뿌리기 기능 구현하기

#### 개발 환경
 - eclipse
 - Java 8
 - Spring boot 2.3.1
 - JPA
 - H2 DB
 - Gradle
 
#### 실행

```
./gradlew clean build
java -jar build/libs/kakaopay-service-0.0.1-SNAPSHOT.jar
````

#### 구현(간소화된 REST API)
 - 1. 뿌리기 API
    `POST /monies/allotment`
 - 2. 받기 API
    `PUT /monies/dividend`
 - 3. 조회 API
    `GET /monies/allotment/{token}`

    