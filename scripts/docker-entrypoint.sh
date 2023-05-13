#!/bin/bash
echo "wait DB container up"
dockerize -wait tcp://db:3306 -timeout 20s

echo "run dongne-api application"
java -jar -Dspring.profiles.active=${PROFILE_OPTION} dongne-api.jar