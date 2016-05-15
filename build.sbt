lazy val root = (project in file(".")).
  settings(
    name := "periscope",
    version := "1.0",
    scalaVersion := "2.11.8",
    mainClass in Compile := Some("main.scala.periscope.App"),
    assemblyJarName := "periscope.jar"
  )

mergeStrategy in assembly <<= (mergeStrategy in assembly) { (old) =>
  {
    case PathList("META-INF", xs @ _*) => MergeStrategy.discard
    case x => MergeStrategy.first
  }
}