#!/bin/sh

export COMPOSE_FILE_PATH="${PWD}/target/classes/docker/docker-compose.yml"
echo "${PWD}/target/classes/docker/docker-compose.yml"
echo "${M2_HOME}"
echo "$COMPOSE_FILE_PATH"
if [ -z "${M2_HOME}" ]; then
  export MVN_EXEC="mvn"
else
  export MVN_EXEC="${M2_HOME}/bin/mvn"
fi

start() {
    docker volume create uniovi-expedientes-acs-volume
    docker volume create uniovi-expedientes-db-volume
    docker volume create uniovi-expedientes-ass-volume
    docker compose -f "$COMPOSE_FILE_PATH" up --build -d
}

start_share() {
    docker compose -f "$COMPOSE_FILE_PATH" up --build -d uniovi-expedientes-share
}

start_acs() {
    docker compose -f "$COMPOSE_FILE_PATH" up --build -d uniovi-expedientes-acs
}

down() {
    if [ -f "$COMPOSE_FILE_PATH" ]; then
        docker compose -f "$COMPOSE_FILE_PATH" down
    fi
}

purge() {
    docker volume rm -f uniovi-expedientes-acs-volume
    docker volume rm -f uniovi-expedientes-db-volume
    docker volume rm -f uniovi-expedientes-ass-volume
}

build() {
    $MVN_EXEC clean package
}

build_share() {
    docker compose -f "$COMPOSE_FILE_PATH" kill uniovi-expedientes-share
    yes | docker compose -f "$COMPOSE_FILE_PATH" rm -f uniovi-expedientes-share
    $MVN_EXEC clean package -pl uniovi-expedientes-share,uniovi-expedientes-share-docker
}

build_acs() {
    docker compose -f "$COMPOSE_FILE_PATH" kill uniovi-expedientes-acs
    yes | docker compose -f "$COMPOSE_FILE_PATH" rm -f uniovi-expedientes-acs
    $MVN_EXEC clean package -pl uniovi-expedientes-integration-tests,uniovi-expedientes-platform,uniovi-expedientes-platform-docker
}

tail() {
    docker compose -f "$COMPOSE_FILE_PATH" logs -f
}

tail_all() {
    docker compose -f "$COMPOSE_FILE_PATH" logs --tail="all"
}

prepare_test() {
    $MVN_EXEC verify -DskipTests=true -pl uniovi-expedientes-platform,uniovi-expedientes-integration-tests,uniovi-expedientes-platform-docker
}

test() {
    $MVN_EXEC verify -pl uniovi-expedientes-platform,uniovi-expedientes-integration-tests
}

case "$1" in
  build_start)
    down
    build
    start
    tail
    ;;
  build_start_it_supported)
    down
    build
    prepare_test
    start
    tail
    ;;
  start)
    start
    tail
    ;;
  stop)
    down
    ;;
  purge)
    down
    purge
    ;;
  tail)
    tail
    ;;
  reload_share)
    build_share
    start_share
    tail
    ;;
  reload_acs)
    build_acs
    start_acs
    tail
    ;;
  build_test)
    down
    build
    prepare_test
    start
    test
    tail_all
    down
    ;;
  test)
    test
    ;;
  *)
    echo "Usage: $0 {build_start|build_start_it_supported|start|stop|purge|tail|reload_share|reload_acs|build_test|test}"
esac
