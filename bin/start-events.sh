#!/bin/bash
set -ex

input="us-counties_1.json"
while IFS= read -r line
do
./bin/docker-compose exec -T kafka_cli kafka-console-producer --broker-list kafka:9092 --topic events "$line"
done < "$input"
