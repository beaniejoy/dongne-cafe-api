#!/bin/bash

# ctrl + c handling
trap "echo -e '\nThe script is terminated'; exit" SIGINT

# get flyway command from first argument
COMMAND=$1

# flyway telemetry disabled
export REDGATE_DISABLE_TELEMETRY=true

PROJECT_NAME="dongne-cafe-api"
PROJECT_ROOT_DIR=$(pwd)
FLYWAY_CONFIG_FILE="${PROJECT_ROOT_DIR}/flyway/flyway-local.conf"
HELP_FILE="${PROJECT_ROOT_DIR}/scripts/migrate/migration-local.help"

repeat() {
  local start=1
  local end=$2
  local range=$(seq $start $end)
	for i in $range; do echo -n "$1"; done
}

print_func_delimiter() {
  repeat '#' 50; echo;
  repeat '#' 14; echo -n " [LOCAL] flyway ${COMMAND} "; repeat '#' `expr 19 - ${#COMMAND}`
  echo
}

check_project_root_path() {
  if [[ ${PROJECT_ROOT_DIR} != *"/${PROJECT_NAME}" ]];
  then
    echo "Error >> move to project's root directory"
    exit 1
  fi

  echo -e "Project's Root Directory: ${PROJECT_ROOT_DIR}\n"
}

flyway_version_check() {
  echo "${FUNC_DELIMITER}"
  echo "Using Flyway Version"

  if ! flyway --version 2> /dev/null;
  then
    echo "Error >> Flyway Not Supported"
    exit 127
  fi
  echo -e "${FUNC_DELIMITER}\n"
}

error_check() {
  if [ $? -ne 0 ];
  then
    echo "Error >> line: $1"
  fi

  printf "\n"
}

# flyway info
flyway_info() {
  trap "error_check $LINENO; after_process; exit" ERR

  print_func_delimiter

  STEP_1="1. Flyway Info"

  echo ${STEP_1}
  flyway info -configFiles="${FLYWAY_CONFIG_FILE}"
}

# flyway migrate
flyway_migration_process() {
  trap "error_check $LINENO; after_process; exit" ERR

  print_func_delimiter

  STEP_1="1. Flyway Info"
  STEP_2="2. Flyway Migrate"
  STEP_3="3. Flyway Validate"

  echo "${STEP_1}"
  flyway info -configFiles="${FLYWAY_CONFIG_FILE}"

  echo

  echo "${STEP_2}"
  flyway migrate -configFiles="${FLYWAY_CONFIG_FILE}"

  echo

  echo "${STEP_3}"
  flyway validate -configFiles="${FLYWAY_CONFIG_FILE}"
}

# flyway repair
flyway_repair() {
  trap "error_check $LINENO; after_process; exit" ERR

  print_func_delimiter

  STEP_1="1. Flyway Repair"
  flyway repair -configFiles="${FLYWAY_CONFIG_FILE}"
}

# flyway clean
flyway_clean() {
  trap "error_check $LINENO; after_process; exit" ERR

  print_func_delimiter

  STEP_1="1. Flyway Clean"
  flyway clean -configFiles="${FLYWAY_CONFIG_FILE}"
}

# delete flyway report files
RETRY_COUNT_MAX=2
RETRY_COUNT=0
remove_report_files() {
  if [ ${RETRY_COUNT} -gt ${RETRY_COUNT_MAX} ];
  then
    echo "No report files. but you have to check."
    return
  fi

  sleep 1

  REPORT_HTML=${PROJECT_ROOT_DIR}/report.html
  REPORT_JSON=${PROJECT_ROOT_DIR}/report.json
  if [[ -e ${REPORT_HTML} || -e ${REPORT_JSON} ]];
  then
    echo "report files found! now remove them"
    rm ${REPORT_HTML} ${REPORT_JSON}
  else
    echo "[${RETRY_COUNT}] No report files"
    RETRY_COUNT=$(expr ${RETRY_COUNT} + 1)
    remove_report_files
  fi
}

after_process() {
  echo "${FUNC_DELIMITER}"

  remove_report_files

  echo "${FUNC_DELIMITER}"
  date +%Y-%m-%d_%H:%M:%S
}

# main block
main() {
  # print help file
  if [[ ${COMMAND} == "help" ]];
  then
    cat "${HELP_FILE}"
    exit 0
  fi

  check_project_root_path

  flyway_version_check

  trap "error_check $LINENO; after_process; exit" ERR

  case "${COMMAND}" in
    migrate)
      flyway_migration_process
      ;;
    repair)
      flyway_repair
      ;;
    clean)
      flyway_clean
      ;;
    info)
      flyway_info
      ;;
    *)
      echo "${COMMAND} > Not supported command"
      exit 40;
  esac

  after_process
}

main
