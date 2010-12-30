import sbt._

class SbtCoverageProject(info: ProjectInfo) extends ProcessorProject(info) with posterous.Publish {

  val undercover = "undercover" % "undercover" % "0.8.3"

  val undercoverRepo = "undercover-repo" at "http://undercover.googlecode.com/svn/maven/repository/"

  lazy val publishTo = Resolver.file("Maven Repository", new java.io.File("maven"))
  
}
