# 동네 카페 전용 사이렌 오더 서비스

개인 프로젝트
- [dongne-cafe-web(프론트 개발)](https://github.com/beaniejoy/dongne-cafe-web)

<br>

## :pushpin: Specification
- Lang
  - java 17
  - kotlin 1.6.21
- Framework
  - Spring Boot 2.7.0
- DB
  - MySQL 8.0.21
  - Flyway(migration)
- CI/CD
  - Jenkins
- Cloud Server
  - AWS Lightsail(Amazon Linux2)

<br>

## :pushpin: Multi Modules
- `dongne-account-api`
  - 회원가입, 인증 관련 내용
- `dongne-service-api`
  - 카페 관련 도메인 내용(추후 주문, 결제 적용 계획)
- `dongne-common`
  - entity, repository, error, security(jwt util) 등 관리하는 공통모듈
- `db`
  - flyway migration 관리 디렉토리

<br>

## :pushpin: Setting

### 💽 로컬 DB 구성 (docker)
- local에 DB(MySQL)용 docker container run
- application은 IDE에서 실행 (default profile: `local`)
```bash
$ docker run --name mysql-server -e MYSQL_ROOT_PASSWORD=beaniejoy -d -p 3306:3306 mysql:8.0.21
```

### 💽 DB Migration (with flyway)
- [DB migration directory README](https://github.com/beaniejoy/dongne-cafe-api/blob/main/db/README.md)

### 💽 docker compose 실행(수정 작업 진행중)
- docker compose를 이용한 nginx, DB(MySQL), application 한꺼번에 실행하는 경우
```bash
$ docker-compose up --build
```
