/*
 * Copyright (c) ProInnovate Limited, 2010
 */

import sbt._

class SbtCoverageProject(info: ProjectInfo) extends ProcessorProject(info) with posterous.Publish {

  val undercover = "undercover" % "undercover" % "0.8.3" % "compile"

  val undercoverRepo = "undercover-repo" at "http://undercover.googlecode.com/svn/maven/repository/"

  // Testing...
  val scalatest = "org.scalatest" % "scalatest" % "1.1" % "test"

  // postereous.Publish configuration...
  override def extension = ".md"
  override def extraTags = "sbt" :: "undercover" :: "processor" :: super.extraTags

  // Maven publishing configuration...
  override def managedStyle = ManagedStyle.Maven
  val publishTo = "Scala Tools Nexus" at "http://nexus.scala-tools.org/content/repositories/releases/"
  Credentials(Path.userHome / ".ivy2" / ".credentials", log)

}
