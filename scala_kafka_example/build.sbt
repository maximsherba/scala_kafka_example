version := "0.1.0-SNAPSHOT"

scalaVersion := "2.13.4"

name := "kafka_client"

libraryDependencies ++= Seq(
  "org.apache.kafka" % "kafka-clients" % "2.6.0",
  "com.fasterxml.jackson.core" % "jackson-databind" % "2.4.0",
  "ch.qos.logback" % "logback-classic" % "1.2.3"
)

libraryDependencies += "org.apache.commons" % "commons-csv" % "1.9.0"

val circeVersion = "0.14.3"
libraryDependencies ++= Seq(
  "io.circe" %% "circe-core",
  "io.circe" %% "circe-generic",
  "io.circe" %% "circe-parser"
).map(_ % circeVersion)
