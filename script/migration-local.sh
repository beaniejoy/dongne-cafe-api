#!/bin/bash

COMMAND=$1

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
  echo "########### [LOCAL] DB Migration ###########"

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

flyway_repair() {
  echo "########### [LOCAL] Repair Flyway Migration ###########"

  STEP_1="1. Flyway Repair"
  flyway repair -configFiles="$PROJECT_ROOT_DIR/db/$FLYWAY_CONFIG_FILE" -outputType=json
  error_check "$STEP_1"
}

flyway_clean() {
  echo "########### [LOCAL] Repair Flyway Clean ###########"

  STEP_1="1. Flyway Clean"
  flyway clean -configFiles="$PROJECT_ROOT_DIR/db/$FLYWAY_CONFIG_FILE" -outputType=json
  error_check "$STEP_1"
}

check_project_root_path
flyway_version_check

case "$COMMAND" in
  migrate)
    flyway_migration_process
    ;;
  repair)
    flyway_repair
    ;;
  clean)
    flyway_clean
    ;;
esac

echo -n "##### "
date +%Y-%m-%d_%H:%M:%S
