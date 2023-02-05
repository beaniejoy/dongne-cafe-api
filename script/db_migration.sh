#!/bin/bash

cd ..

./gradlew :db:flywayInfo

./gradlew :db:flywayValidate