import sbt._

class Plugins(info: ProjectInfo) extends PluginDefinition(info) {
  val posterous = "net.databinder" % "posterous-sbt" % "0.1.6"
  
  // Required for knockoff for posterous...
  val t_repo = "t_repo" at "http://tristanhunt.com:8081/content/groups/public/"
  // Required for snuggletex for posterous...
  val snuggletex_repo = "snuggletex_repo" at "http://www2.ph.ed.ac.uk/maven2"
}
