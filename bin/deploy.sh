#!/bin/sh

spark-submit --master spark://master:7077 \
  --num-executors 2 --executor-cores 2 --executor-memory 512M \
  --class wordcount.SparkWordCount \
  --name  SparkWordCount\
  --files README.md\
  build/spark_word_count.jar
