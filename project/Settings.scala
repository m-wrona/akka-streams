import sbt._
import Keys._

/**
 * Build settings
 */
object BuildSettings {

  val buildOrganization = "com.mwronski.streams"
  val buildVersion = "0.0.1"
  val buildScalaVersion = "2.10.4"

  /**
   * Settings
   */
  val buildSettings = Seq(
    organization := buildOrganization,
    version := buildVersion,
    scalaVersion := buildScalaVersion,
    shellPrompt := ShellPrompt.buildShellPrompt
  )

  /**
   * Create settings for chosen module
   * @param module module name
   * @return non-nullable sequence
   */
  def moduleSettings(module: String) = Seq(
    organization := buildOrganization + "." + module,
    version := buildVersion,
    scalaVersion := buildScalaVersion,
    shellPrompt := ShellPrompt.buildShellPrompt
  )
}
