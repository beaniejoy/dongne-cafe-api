#!/bin/bash

PROJECT_NAME="dongne-cafe-api"
PROJECT_ROOT_DIR=$(pwd)
FLYWAY_CONFIG_FILE="flyway-local.conf"

check_project_root_path() {
  if [[ $PROJECT_ROOT_DIR != *"/$PROJECT_NAME" ]];
  then
    echo "Error >> move to project's root directory"
    exit 1
  fi

  echo -e "Project's Root Directory: $PROJECT_ROOT_DIR\n"
}

flyway_version_check() {
  echo "###################################"
  echo "Using Flyway Version"

  if ! flyway --version 2> /dev/null;
  then
    echo "Error >> Flyway Not Supported"
    exit 1
  fi
  echo -e "###################################\n"
}

error_check() {
  if [ $? -ne 0 ];
  then
    echo "Error >> $1 & Exit"
    exit 1
  fi

  printf "\n"
}

flyway_migration_process() {
  STEP_1="1. Flyway Info"
  STEP_2="2. Flyway Migrate"
  STEP_3="3. Flyway Validate"

  echo $STEP_1
  flyway info -configFiles="$PROJECT_ROOT_DIR/db/$FLYWAY_CONFIG_FILE"
  error_check "$STEP_1"

  echo $STEP_2
  flyway migrate -configFiles="$PROJECT_ROOT_DIR/db/$FLYWAY_CONFIG_FILE" -outputType=json
  error_check "$STEP_2"

  echo $STEP_3
  flyway validate -configFiles="$PROJECT_ROOT_DIR/db/$FLYWAY_CONFIG_FILE" -outputType=json
  error_check "$STEP_3"
}

echo "########### [LOCAL] DB Migration ###########"
check_project_root_path
flyway_version_check
flyway_migration_process