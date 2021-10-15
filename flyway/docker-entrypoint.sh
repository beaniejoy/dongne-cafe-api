#!/bin/bash
echo "wait DB container up"
dockerize -wait tcp://db-mysql:3306 -timeout 20s

# DB Migration
echo "run database migration"
flyway -configFiles=/flyway/conf/flyway_main.conf -locations=filesystem:/flyway/sql/main migrate

# Seed Migration
echo "insert seed data"
flyway -configFiles=/flyway/conf/flyway_seed.conf -locations=filesystem:/flyway/sql/seed migrate
