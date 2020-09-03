package wordcount

import org.apache.spark.sql.SparkSession

object SparkWordCount extends App {
  val spark: SparkSession = SparkSession.builder().getOrCreate()

  import spark.implicits._
  val text_file = spark.read.textFile("words.txt")
  val count = text_file
    .flatMap(line => line.split(" "))
    .count()

  println(s"************** Total Words : $count")
}