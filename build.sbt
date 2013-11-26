name := "appmarket"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache,
  "commons-io" % "commons-io" % "2.0",
  "org.apache.httpcomponents" % "httpclient" % "4.2.3",
      "org.jsoup" % "jsoup" % "1.7.2",
      "mysql" % "mysql-connector-java" % "5.1.21"
)     

play.Project.playJavaSettings
