name := """play-scala-starter-example"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

resolvers += Resolver.sonatypeRepo("snapshots")
resolvers += "reactivecouchbase-rs-snapshots" at "https://raw.github.com/ReactiveCouchbase/reactivecouchbase-rs-core/master/repository/snapshots"
resolvers += "reactivecouchbase-rs-releases" at "https://raw.github.com/ReactiveCouchbase/reactivecouchbase-rs-core/master/repository/releases"



scalaVersion := "2.12.4"

crossScalaVersions := Seq("2.11.12", "2.12.4")

libraryDependencies += guice
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "3.1.2" % Test
libraryDependencies += "com.h2database" % "h2" % "1.4.196"

libraryDependencies += "org.reactivecouchbase" %% "reactivecouchbase-rs-core" % "1.0.0"

