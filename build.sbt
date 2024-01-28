import org.scalajs.linker.interface.ModuleSplitStyle.SmallModulesFor

ThisBuild / version       := "0.1.0"
ThisBuild / scalaVersion  := "3.3.1"
ThisBuild / versionScheme := Some("early-semver")

val publicFolderDev  = taskKey[String]("Returns the compiled main.js parent path for dev")
val publicFolderProd = taskKey[String]("Returns the compiled main.js parent path for production`")

lazy val root = project
  .in(file("."))
  .enablePlugins(ScalaJSPlugin)
  .settings(
    resolvers += "jitpack" at "https://jitpack.io",
    libraryDependencies ++= Seq(
      "com.raquo"                               %%% "laminar"              % Dependencies.laminar,
      "com.raquo"                               %%% "waypoint"             % Dependencies.waypoint,
      "com.lihaoyi"                             %%% "upickle"              % Dependencies.upickle,
      "org.scala-js"                            %%% "scalajs-dom"          % "2.4.0",
      "com.github.fdietze.scala-js-fontawesome" %%% "scala-js-fontawesome" % "a412650e7f"
    ),
    scalaJSUseMainModuleInitializer := true,
    scalaJSLinkerConfig ~= {
      _.withModuleKind(ModuleKind.ESModule)
        .withModuleSplitStyle(SmallModulesFor(List("io.scala")))
        .withSourceMap(false)
    },
    publicFolderDev  := linkerOutputDirectory((Compile / fastLinkJS).value).getAbsolutePath,
    publicFolderProd := linkerOutputDirectory((Compile / fullLinkJS).value).getAbsolutePath,
    Compile / sourceGenerators += Def.task {
      val file = (sourceManaged in Compile).value / "io" / "scala" / "data" / "MarkdownSource.scala"

      val markdowns: Seq[File] = (Compile / resourceDirectory).value.listFiles().filter(_.getName.endsWith(".md")).toList

      def lines(lines: String*) = lines.mkString("\n")
      def slugify(name: String): String = name.toLowerCase().replace(" ", "-")

      val content: String = lines(
        "package io.scala.data",
        "",
        "object MarkdownSource {",
        "  case class MarkdownSource(name:String, content:String)",
        "",
        lines(markdowns.map(file => {
          val name: String = slugify(file.getName.replace(".md", ""))
          val content: String = "\"" * 3 + IO.read(file) + "\"" * 3

          s"""  val $name = MarkdownSource("$name", $content)"""
        }) *),
        "}"
      )

      if (!file.exists() || IO.read(file) != content) {
        IO.write(file, content)
      }

      Seq(file)
    }.taskValue
  )

def linkerOutputDirectory(v: Attributed[org.scalajs.linker.interface.Report]): File = {
  v.get(scalaJSLinkerOutputDirectory.key).getOrElse {
    throw new MessageOnlyException(
      "Linking report was not attributed with output directory. " +
        "Please report this as a Scala.js bug."
    )
  }
}
