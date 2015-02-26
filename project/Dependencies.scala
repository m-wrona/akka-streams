import sbt._

object Resolvers {

  val sunRepo = "Sun Maven2 Repo" at "http://download.java.net/maven/2"
  val sonatypeRelease = "Sonatype release" at "https://oss.sonatype.org/content/repositories/releases"

  val projectResolvers = Seq(sunRepo, sonatypeRelease)
}

object GenericDependencies {

  //time support
  val jodaTime = "joda-time" % "joda-time" % "2.4"
  val jodaConvert = "org.joda" % "joda-convert" % "1.2"

  //testing
  val scalaTest = "org.scalatest" % "scalatest_2.10" % "2.2.1" % Test
  val scalaMock = "org.scalamock" % "scalamock_2.10" % "3.2.1" % Test
  val scalaMockSupport = "org.scalamock" %% "scalamock-scalatest-support" % "3.2.1" % Test

  val commonDeps = Seq(
    jodaTime,
    jodaConvert,
    scalaTest,
    scalaMock,
    scalaMockSupport
  )
}

object AkkaDependencies {

  val akkaVersion = "2.3.5"

  val akkaActor = "com.typesafe.akka" %% "akka-actor" % akkaVersion
  val akkaTestKit = "com.typesafe.akka" %% "akka-testkit" % akkaVersion % Test
  val akkaStreams = "com.typesafe.akka" %% "akka-stream-experimental" % "1.0-M3"
  val akkaStreamsTestKit = "com.typesafe.akka" %% "akka-stream-testkit-experimental" % "1.0-M3"


  val allDeps = Seq(
    akkaActor,
    akkaStreams,
    akkaTestKit,
    akkaStreamsTestKit
  )
}
