val scala3Version = "3.5.2"

lazy val root = project
  .in(file("."))
  .settings(
    name := "probl3.1",
    version := "0.1.0-SNAPSHOT",

    scalaVersion := scala3Version,

    libraryDependencies += "org.scalameta" %% "munit" % "1.0.0" % Test, 
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.14" % Test
  )
