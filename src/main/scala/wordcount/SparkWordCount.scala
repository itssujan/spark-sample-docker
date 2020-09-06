package wordcount

import java.util.Scanner

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.types.{
  DateType,
  IntegerType,
  StringType,
  StructType
}

object SparkWordCount extends App {
  val spark: SparkSession = SparkSession.builder().getOrCreate()

  val covidDataSchema = new StructType()
    .add("date", DateType)
    .add("county", StringType)
    .add("state", StringType)
    .add("fips", IntegerType)
    .add("cases", IntegerType)
    .add("deaths", IntegerType)

  import spark.implicits._
  val df =
    spark.read
      .format("csv")
      .option("header", "true")
      .schema(covidDataSchema)
      .load("us-counties.csv")
      .drop("deaths")

  df.printSchema()
  println(s"Total Count : ${df.count()}")
  df.sort(df("date").desc).filter(df("county") === "Wake").show(50)

  new Scanner(System.in).nextLine() // to make the spark ui wait

}
