# DB Migration

- flyway version: `9.15.4`
- [flyway doc](https://documentation.red-gate.com/fd/flyway-documentation-138346877.html)

<br>

## :pushpin: Installation(Local)
LOCAL 환경에 해당
```shell
$ brew install flyway
```
- macOS 전용

<br>

## :pushpin: Flyway Command

- **Clean**  
  Drops all objects (tables, views, procedures, triggers, …) in the configured schemas  
  (prodution 단계에서는 절대 사용 X)
```bash
$ flyway clean -configFiles=db/flyway.conf
```

- **Info**  
  Prints the details and status information about all the migrations
```bash
$ flyway info -configFiles=db/flyway.conf
```

- **Migrate**  
  Migrates the schema to the latest version
  migration 설정 내용들 반영
```bash
$ flyway migrate -configFiles=db/flyway.conf
```

- **Validate**  
  Validates the applied migrations against the available ones  
  DB에 적용된 migration과 local에 적용된 migration 정보 일치 여부 체크
```bash
$ flyway validate -configFiles=db/flyway.conf
```

<br>

## :pushpin: Migration for Local Env

```shell
$ cd [PROJECT_ROOT_DIR]

$ chmod 755 ./script/migration-local.sh
$ ./script/migration-local.sh
```
project의 root directory로 이동하는 것이 중요