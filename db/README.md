# DB Migration

- flyway version: `9.15.4`
- [flyway doc](https://documentation.red-gate.com/fd/flyway-documentation-138346877.html)

<br>

## :pushpin: Installation(Local) - not necessary
(불필요) LOCAL 환경에 해당
```shell
$ brew install flyway
```
- macOS 전용

<br>

## :pushpin: Flyway Command(Deprecated)

**Gradle Flyway plugin으로 대체**
- **Clean**
  Drops all objects (tables, views, procedures, triggers, …) in the configured schemas
  (prodution 단계에서는 절대 사용 X)
```bash
$ flyway clean -configFiles=db/flyway.conf (not used)
$ ./gradlew :db:flywayClean -Dprofile=${spring.profiles.active}
```

- **Info**
  Prints the details and status information about all the migrations
```bash
$ flyway info -configFiles=db/flyway.conf (not used)
$ ./gradlew :db:flywayInfo -Dprofile=${spring.profiles.active}
```

- **Migrate**
  Migrates the schema to the latest version
  migration 설정 내용들 반영
```bash
$ flyway migrate -configFiles=db/flyway.conf (not used)
$ ./gradlew :db:flywayMigrate -Dprofile=${spring.profiles.active}
```

- **Validate**
  Validates the applied migrations against the available ones
  DB에 적용된 migration과 local에 적용된 migration 정보 일치 여부 체크
```bash
$ flyway validate -configFiles=db/flyway.conf (not used)
$ ./gradlew :db:flywayValidate -Dprofile=${spring.profiles.active}
```

<br>

## :pushpin: Migration for Local Env(Deprecated)

```shell
$ cd [PROJECT_ROOT_DIR]

$ chmod 755 ./scripts/migrate/migration-local.sh
$ ./scripts/migrate/migration-local.sh
```
project의 root directory로 이동하는 것이 중요
