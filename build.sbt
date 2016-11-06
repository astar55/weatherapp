name := """play-java-intro"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  // If you enable PlayEbean plugin you must remove these
  // JPA dependencies to avoid conflicts.
  javaJpa,
  "org.hibernate" % "hibernate-entitymanager" % "4.3.7.Final",
  // https://mvnrepository.com/artifact/javax.json/javax.json-api
  "javax.json" % "javax.json-api" % "1.0",
  // https://mvnrepository.com/artifact/org.glassfish/javax.json
  "org.glassfish" % "javax.json" % "1.0.4",
  "org.json" % "json" % "20160810"
)



fork in run := true