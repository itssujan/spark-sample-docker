name := "spark_word_count"

scalaVersion := "2.11.11"
val sparkVersion = "2.4.6"

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % sparkVersion % "provided",
  "org.apache.spark" %% "spark-sql" % sparkVersion % "provided"
)

target in assembly := file("build")

assemblyJarName in assembly := name.value + ".jar"
