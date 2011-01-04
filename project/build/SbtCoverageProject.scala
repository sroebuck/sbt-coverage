/*
 * Copyright (c) ProInnovate Limited, 2010
 */

import sbt._

class SbtCoverageProject(info: ProjectInfo) extends ProcessorProject(info) with posterous.Publish {

  val undercover = "undercover" % "undercover" % "0.8.3" % "compile"

  val undercoverRepo = "undercover-repo" at "http://undercover.googlecode.com/svn/maven/repository/"

  // FIXME:  I don't think anything that follows does anything of value!
  // repository config for publishing
  override def managedStyle = ManagedStyle.Maven
  lazy val publishTo = Resolver.file("Maven Repository", new java.io.File("maven"))
  
}
