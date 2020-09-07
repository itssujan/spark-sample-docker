#!/bin/sh

spark-submit --master spark://sparkmaster:7077 \
  --num-executors 2 --executor-cores 2 --executor-memory 512M \
  --class covid.CovidDataProcesser \
  --name  CovidDataProcesser\
  --files README.md\
  --packages org.apache.spark:spark-sql-kafka-0-10_2.11:2.3.0\
  build/spark_covid_data_processor.jar
