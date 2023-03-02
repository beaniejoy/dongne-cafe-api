#!/bin/bash

echo "================ 1. Flyway Info ================"
./gradlew :db:flywayInfo -Dconfig=$1

echo "================ 2. Flyway Validate ============"
./gradlew :db:flywayValidate -Dconfig=$1

echo "================ 3. Flyway Validate ============"
./gradlew :db:flywayMigrate -Dconfig=$1

