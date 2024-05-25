import java.io.File
import org.scalajs.linker.interface.ModuleSplitStyle.SmallestModules

ThisBuild / version       := "0.1.0"
ThisBuild / scalaVersion  := "3.4.2"
ThisBuild / versionScheme := Some("early-semver")

val publicFolderDev  = taskKey[String]("Returns the compiled main.js parent path for dev")
val publicFolderProd = taskKey[String]("Returns the compiled main.js parent path for production`")
val eventLister      = taskKey[File]("List all events markdown files")

lazy val root = project
  .in(file("."))
  .enablePlugins(ScalaJSPlugin)
  .settings(
    name := "scalaio-website",
    // scalacOptions += "-Yexplicit-nulls",
    scalacOptions += "-Wunused:all",
    libraryDependencies ++= Seq(
      "com.raquo"                     %%% "laminar"           % Dependencies.laminar,
      "com.raquo"                     %%% "waypoint"          % Dependencies.waypoint,
      "com.lihaoyi"                   %%% "upickle"           % Dependencies.upickle,
      "org.scala-js"                  %%% "scalajs-dom"       % "2.4.0",
      "org.foundweekends"             %%% "knockoff"          % "0.9.0",
      "com.softwaremill.sttp.tapir"   %%% "tapir-sttp-client" % "1.10.7",
      "com.softwaremill.sttp.client4" %%% "core"              % "4.0.0-M14",
      "io.github.cquiroz" %%% "scala-java-time" % "2.5.0" // implementations of java.time classes for Scala.JS
    ),
    scalaJSUseMainModuleInitializer := true,
    scalaJSLinkerConfig ~= {
      _.withModuleKind(ModuleKind.ESModule)
        .withModuleSplitStyle(SmallestModules)
        .withSourceMap(false)
    },
    publicFolderDev  := linkerOutputDirectory((Compile / fastLinkJS).value).getAbsolutePath,
    publicFolderProd := linkerOutputDirectory((Compile / fullLinkJS).value).getAbsolutePath,
    Compile / sourceGenerators += eventListing().taskValue,
    Compile / sourceGenerators += Def.task {
      val file = (Compile / sourceManaged).value / "io" / "scala" / "data" / "MarkdownSource.scala"

      val markdowns: Seq[File] = listMarkdown((Compile / resourceDirectory).value / "md")

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

def lines(lines: String*)               = lines.mkString("\n")
def slugify(name: String): String       = name.toLowerCase().replace("-", "_")
def listMarkdown(file: File): Seq[File] = file.listFiles().filter(_.getName.endsWith(".md")).toList

def eventListing() = Def.task {
  val file   = (Compile / sourceManaged).value / "io" / "scala" / "data" / "EventFilesName.scala"
  val events = listMarkdown(new File("./public/scalafr-meetups"))
  val content =
    s"""|package io.scala.data
       |
       |object EventFilesName:
       |  val names = List(${events.map(file => s""""${file.getName()}"""").mkString(",")})
       |""".stripMargin

  if (!file.exists() || IO.read(file) != content) {
    IO.write(file, content)
  }
  Seq(file)
}

def linkerOutputDirectory(v: Attributed[org.scalajs.linker.interface.Report]): File = {
  v.get(scalaJSLinkerOutputDirectory.key).getOrElse {
    throw new MessageOnlyException(
      "Linking report was not attributed with output directory. " +
        "Please report this as a Scala.js bug."
    )
  }
}
