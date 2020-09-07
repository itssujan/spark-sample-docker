#!/bin/bash
set -ex
./bin/docker-compose exec kafka_cli kafka-console-consumer --zookeeper zookeeper:2181 $*
