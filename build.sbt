name := """tweensy-weensy"""
organization := "org.charik"

version := "1.0.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.12.10"

libraryDependencies += guice
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "5.0.0" % Test

libraryDependencies += "org.picoworks" %% "pico-hashids"  % "4.4.141"
libraryDependencies += "net.debasishg" %% "redisclient" % "3.7"

// Adds additional packages into Twirl
//TwirlKeys.templateImports += "org.charik.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "org.charik.binders._"
