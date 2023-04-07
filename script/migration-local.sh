#!/bin/bash

echo -e "########### [LOCAL] DB Migration ###########"
printf "\n"

PROJECT_NAME="dongne-cafe-api"
PROJECT_ROOT_DIR=$(pwd)
FLYWAY_CONFIG_FILE="flyway-local.conf"

if [[ $PROJECT_ROOT_DIR != *"/$PROJECT_NAME" ]];
then
  echo "Error >> move to project's root directory"
  exit 1
fi

echo "Project's Root Directory: $PROJECT_ROOT_DIR"
printf "\n"

echo "###################################"
echo "Using Flyway Version"

if ! flyway --version 2> /dev/null;
then
  echo "Error >> Flyway Not Supported"
  exit 1
fi
echo "###################################"
printf "\n"

echo "1. Flyway Info"
flyway info -configFiles="$PROJECT_ROOT_DIR/db/$FLYWAY_CONFIG_FILE"
printf "\n"

echo "2. Flyway Migrate"
flyway migrate -configFiles="$PROJECT_ROOT_DIR/db/$FLYWAY_CONFIG_FILE" -outputType=json
printf "\n"

echo "3. Flyway Validate"
flyway validate -configFiles="$PROJECT_ROOT_DIR/db/$FLYWAY_CONFIG_FILE" -outputType=json
