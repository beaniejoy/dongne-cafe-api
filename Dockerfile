FROM openjdk:17-alpine as BUILD_IMAGE

ENV WORK_DIR=/usr/app/

# app 작업 디렉토리 설정
WORKDIR $WORK_DIR

# gradle 실행을 위한 필수 디렉토리 준비
COPY gradlew $WORK_DIR
COPY build.gradle $WORK_DIR
COPY settings.gradle $WORK_DIR
COPY gradle $WORK_DIR/gradle

RUN ./gradlew -x test build || return 0

COPY src src

# jar 파일 build
RUN ./gradlew bootjar

FROM openjdk:17-alpine

ENV WORK_DIR=/usr/app/

WORKDIR $WORK_DIR

COPY --from=BUILD_IMAGE $WORK_DIR/build/libs/*.jar dongne-api.jar

ENTRYPOINT ["java", \
"-jar", \
"-Dspring.profiles.active=${PROFILE_OPTION}", \
"dongne-api.jar"]