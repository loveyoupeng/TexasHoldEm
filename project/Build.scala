import sbt._
import sbt.Keys._

object ProjectBuild extends Build {

  lazy val root = Project(
    id = "root",
    base = file("."),
    settings = Project.defaultSettings ++ Seq(
      name := "TexasHoldEm",
      organization := "org.loveyoupeng.game.texasholdem",
      version := "0.1-SNAPSHOT",
      scalaVersion := "2.9.2",
      scalacOptions += "-deprecation",
      resolvers += "Typesafe Releases" at "http://repo.typesafe.com/typesafe/releases",
      libraryDependencies += "com.typesafe.akka" % "akka-actor" % "2.0",
      libraryDependencies += "org.scalacheck" % "scalacheck" % "1.5" % "test",
      libraryDependencies += "org.specs2" % "specs2_2.9.1" % "1.8.2" % "test",
      libraryDependencies += "org.scalatest" % "scalatest_2.9.1-1" % "1.7.1" % "test",
      libraryDependencies += "junit" % "junit" % "4.9" % "test"
    )
  )
}
