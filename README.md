# 동네 카페 전용 사이렌 오더 서비스

개인 프로젝트
- [dongne-cafe-web(프론트 개발)](https://github.com/beaniejoy/dongne-cafe-web)

<br>

## Specification
- java 17
- kotlin 1.6.21
- Spring Boot 2.7.0
- MySQL 8.0.21

<br>

## :pushpin: Multi Modules
- `dongne-account-api`
  - 회원가입, 인증 관련 내용
- `dongne-service-api`
  - 카페 관련 도메인 내용(추후 주문, 결제 적용 계획)
- `dongne-common`
  - entity, repository, error, security(jwt util) 등 관리하는 공통모듈
- `db`
  - flyway migration(gradle) 적용 모듈

<br>

## :pushpin: Run Application

### 로컬 환경 내 로컬 DB 따로 구성
- local에 DB(MySQL)용 docker container run
- application은 IDE에서 실행 (default profile: `local`)
```bash
$ docker run --name beanie-test-db -e MYSQL_ROOT_PASSWORD=beaniejoy -d -p 3306:3306 mysql:8.0.21
```

### DB Migration (flyway)
- **Info**  
Prints the details and status information about all the migrations
```bash
$ ./gradlew :db:flywayInfo
```
- **Validate**  
Validates the applied migrations against the available ones  
DB에 적용된 migration과 local에 적용된 migration 정보 일치 여부 체크
```bash
$ ./gradlew :db:flywayValidate
```
- **Migrate**  
Migrates the schema to the latest version
migration 설정 내용들 반영
```bash
$ ./gradlew :db:flywayMigrate
```
- **Clean**  
Drops all objects (tables, views, procedures, triggers, …) in the configured schemas  
(prodution 단계에서는 절대 사용 X)
```bash
$ ./gradlew :db:flywayClean -i
```


### docker compose 실행(수정 작업 진행중)
- docker compose를 이용한 nginx, DB(MySQL), application 한꺼번에 실행하는 경우
```bash
$ docker-compose up --build
```
