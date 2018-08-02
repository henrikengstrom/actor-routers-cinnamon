//#build-sample
// Enable the Cinnamon Lightbend Monitoring sbt plugin
lazy val pingPonger = project in file(".") enablePlugins (Cinnamon)

cinnamon in run := true
cinnamon in test := true

name := "routerexample"

version := "1.0"

scalaVersion := "2.12.6"

libraryDependencies ++= Seq(
  Cinnamon.library.cinnamonSlf4jMdc,
  "com.typesafe.akka" %% "akka-actor" % "2.5.14",
  "org.slf4j" % "slf4j-api" % "1.7.25",
  "ch.qos.logback" % "logback-classic" % "1.2.3" 
)
