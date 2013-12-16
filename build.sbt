name := "appmarket"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache,
  filters,
  "commons-io" % "commons-io" % "2.0",
  "org.apache.httpcomponents" % "httpclient" % "4.2.3",
  "org.jsoup" % "jsoup" % "1.7.2",
  "mysql" % "mysql-connector-java" % "5.1.21",
  "com.clever-age" % "play2-elasticsearch" % "0.8-SNAPSHOT"
)

  resolvers += (
  "play-plugin-snapshots" at "http://repo.scala-sbt.org/scalasbt/sbt-plugin-snapshots/")

play.Project.playJavaSettings
