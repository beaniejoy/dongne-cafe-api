# 동네 카페 전용 사이렌 오더 서비스

개인 프로젝트
- [dongne-cafe-web(프론트 개발)](https://github.com/beaniejoy/dongne-cafe-web)

<br>

## Specification
- java 17
- kotlin 1.6.21
- Spring Boot 2.7.0
- MySQL

<br>

## Run Application

### 로컬 환경 내 로컬 DB 따로 구성
- local에 DB(MySQL)용 docker container run
- application은 IDE에서 실행 (default profile: `local`)
```bash
$ docker run --name beanie-test-db -e MYSQL_ROOT_PASSWORD=beaniejoy -d -p 3306:3306 mysql:5.7.34
```

### docker compose 실행(수정 작업 진행중)
- docker compose를 이용한 nginx, DB(MySQL), application 한꺼번에 실행하는 경우
```bash
$ docker-compose up --build
```
