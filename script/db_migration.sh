#!/bin/bash

echo "#### Flyway Config File: $1"

./gradlew :db:flywayInfo -Dconfig=$1

./gradlew :db:flywayValidate -Dconfig=$1