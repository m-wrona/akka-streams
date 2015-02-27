import sbt._
import Keys._

object ProjectBuild extends Build {

  import Resolvers._
  import BuildSettings._

  private val depCompileTest = "compile->compile;test->test"

  lazy val project = Project(
    "akka-streams",
    file("."),
    settings = buildSettings
  ) aggregate (
    moduleStreams
    )

  lazy val moduleStreams = Project(
    "streams",
    file("streams"),
    settings = moduleSettings("streams") ++
      Seq(
        resolvers := projectResolvers,
        libraryDependencies ++= (
          GenericDependencies.commonDeps ++
            AkkaDependencies.allDeps
          )
      )
  )

}