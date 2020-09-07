#!/bin/bash
set -ex
./bin/docker-compose exec kafka_cli kafka-console-producer --broker-list kafka:9092 $*
