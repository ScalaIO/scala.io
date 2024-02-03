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
    name := "scalaio-website",
    libraryDependencies ++= Seq(
      "com.raquo"         %%% "laminar"     % Dependencies.laminar,
      "com.raquo"         %%% "waypoint"    % Dependencies.waypoint,
      "com.lihaoyi"       %%% "upickle"     % Dependencies.upickle,
      "org.scala-js"      %%% "scalajs-dom" % "2.4.0",
      "org.foundweekends" %%% "knockoff"    % "0.9.0"
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
      val file = (Compile / sourceManaged).value / "io" / "scala" / "data" / "MarkdownSource.scala"

      val markdowns: Seq[File] =
        ((Compile / resourceDirectory).value / "md").listFiles().filter(_.getName.endsWith(".md")).toList

      def lines(lines: String*)         = lines.mkString("\n")
      def slugify(name: String): String = name.toLowerCase().replace("-", "_")

      val content: String = lines(
        "package io.scala.data",
        "",
        "object MarkdownSource {",
        "",
        lines(markdowns.map(file => {
          val name: String    = slugify(file.getName.stripSuffix(".md"))
          val content: String = "\"" * 3 + IO.read(file) + "\"" * 3

          s"""  val $name = $content"""
        })*),
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
