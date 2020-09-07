#/bin/bash
set -ex
REPLICATION_FACTOR=${REPLICATION_FACTOR:-1}
PARTITIONS=${PARTITIONS:-1}
kafka-topics --create --zookeeper zookeeper:2181 --topic events --replication-factor $REPLICATION_FACTOR --partitions $PARTITIONS