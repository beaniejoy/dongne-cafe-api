FROM adoptopenjdk/openjdk11:latest as BUILD_IMAGE

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

FROM adoptopenjdk/openjdk11:latest

ENV WORK_DIR=/usr/app/

WORKDIR $WORK_DIR

COPY --from=BUILD_IMAGE $WORK_DIR/build/libs/*.jar tripleapp.jar

ENTRYPOINT ["java", \
"-jar", \
"-Dspring.profiles.active=${PROFILE_OPTION}", \
"-Dspring.datasource.url=${SPRING_DATASOURCE_URL}", \
"-Dredis.host=${REDIS_HOST}", \
"tripleapp.jar"]