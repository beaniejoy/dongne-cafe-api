FROM openjdk:17-alpine as BUILD_IMAGE

ENV WORK_DIR=/usr/app/

# app 작업 디렉토리 설정
WORKDIR $WORK_DIR

# gradle 실행을 위한 필수 디렉토리 준비
COPY gradlew $WORK_DIR
COPY build.gradle $WORK_DIR
COPY settings.gradle $WORK_DIR
COPY gradle $WORK_DIR/gradle

RUN ./gradlew test build || return 0

COPY src src

# jar 파일 build
RUN ./gradlew bootjar

FROM openjdk:17-alpine

ENV DOCKERIZE_VERSION v0.6.1
ENV WORK_DIR=/usr/app/

WORKDIR $WORK_DIR

COPY --from=BUILD_IMAGE $WORK_DIR/build/libs/*.jar dongne-api.jar

# run in order through dockerize utility (alpine version)
# link - https://github.com/jwilder/dockerize
RUN apk add --no-cache openssl bash

RUN wget https://github.com/jwilder/dockerize/releases/download/$DOCKERIZE_VERSION/dockerize-alpine-linux-amd64-$DOCKERIZE_VERSION.tar.gz \
    && tar -C /usr/local/bin -xzvf dockerize-alpine-linux-amd64-$DOCKERIZE_VERSION.tar.gz \
    && rm dockerize-alpine-linux-amd64-$DOCKERIZE_VERSION.tar.gz

COPY ./script/docker-entrypoint.sh docker-entrypoint.sh

RUN chmod +x docker-entrypoint.sh

ENTRYPOINT ["./docker-entrypoint.sh"]
