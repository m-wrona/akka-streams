import sbt._
import Keys._

object ProjectBuild extends Build {

  import Resolvers._
  import BuildSettings._

  private val depCompileTest = "compile->compile;test->test"

  lazy val hurricane = Project(
    "hurricane",
    file("."),
    settings = buildSettings
  ) aggregate (
    moduleCore
    )

  lazy val moduleCore = Project(
    "hurricane-core",
    file("core"),
    settings = moduleSettings("hurricane.core") ++
      Seq(
        resolvers := projectResolvers,
        libraryDependencies ++= (
          GenericDependencies.commonDeps ++
            AkkaDependencies.allDeps
          )
      )
  )


}