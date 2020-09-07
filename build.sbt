name := "spark_covid_data_processor"

scalaVersion := "2.11.11"
val sparkVersion = "2.3.0"

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % sparkVersion % "provided",
  "org.apache.spark" %% "spark-sql" % sparkVersion % "provided",
  "org.apache.spark" %% "spark-streaming" % sparkVersion % "provided",
  "org.apache.spark" %% "spark-sql-kafka-0-10" % sparkVersion % "provided",
  "org.elasticsearch" % "elasticsearch-hadoop" % "7.4.2",
  "org.elasticsearch" % "elasticsearch" % "7.4.2"
)

target in assembly := file("build")

assemblyJarName in assembly := name.value + ".jar"
