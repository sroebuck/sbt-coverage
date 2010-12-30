import sbt._

class SbtCoverageProject(info: ProjectInfo) extends ProcessorProject(info) with posterous.Publish {
  
  lazy val publishTo = Resolver.file("Maven Repository", new java.io.File("maven"))
  
}
