package covid

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.{col, from_json, _}
import org.apache.spark.sql.types.{
  DateType,
  IntegerType,
  StringType,
  StructType
}

object CovidDataProcesser extends App {
  val spark: SparkSession =
    SparkSession.builder().appName("CovidDataProcesser").getOrCreate()

  val covidDataSchema = new StructType()
    .add("date", StringType)
    .add("county", StringType)
    .add("state", StringType)
    .add("fips", StringType)
    .add("cases", IntegerType)
    .add("deaths", IntegerType)

  val df = spark.readStream
    .format("kafka")
    .option("kafka.bootstrap.servers", "kafka:9092")
    .option("subscribe", "events")
    .load()
    .select(
      from_json(col("value").cast("string"), covidDataSchema)
        .alias("parsed_value")
    )

  val df1 = df
    .select(col("parsed_value.*"))
    .withColumn("state", lower(col("state")))
    .withColumn("state", regexp_replace(col("state"), "\\s+", "_"))
    .toDF()

  df1.writeStream
    .format("org.elasticsearch.spark.sql")
    .outputMode("append")
    .option("es.resource", "st_{state}")
    .option("es.nodes", "elasticsearch")
    .option("checkpointLocation", "checkpoint")
    .start()

  df1.writeStream
    .format("console")
    .start()

  spark.streams.awaitAnyTermination()

}
