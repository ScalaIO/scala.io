import java.io.File
import org.scalajs.linker.interface.ModuleSplitStyle.SmallestModules

ThisBuild / version       := "0.1.0"
ThisBuild / scalaVersion  := "3.4.2"
ThisBuild / versionScheme := Some("early-semver")

val publicFolderDev  = taskKey[String]("Returns the compiled main.js parent path for dev")
val publicFolderProd = taskKey[String]("Returns the compiled main.js parent path for production`")
val eventLister      = taskKey[Seq[File]]("List all events markdown files")
val confLister       = taskKey[Seq[File]]("List all events markdown files")

lazy val root = project
  .in(file("."))
  .enablePlugins(ScalaJSPlugin)
  .settings(
    name := "scalaio-website",
    scalacOptions ++= Seq(
      // "-Yexplicit-nulls",
      "-Wunused:all"
    ),
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
    confLister       := confListing().value,
    publicFolderDev  := linkerOutputDirectory((Compile / fastLinkJS).value).getAbsolutePath,
    publicFolderProd := linkerOutputDirectory((Compile / fullLinkJS).value).getAbsolutePath,
    Compile / sourceGenerators ++= Seq(eventListing().taskValue, confListing().taskValue),
  )

def lines(lines: String*)                = lines.mkString("\n")
def slugify(name: String): String        = name.toLowerCase().replace("-", "_")
def listMarkdowns(file: File): Seq[File] = file.listFiles().filter(_.getName.endsWith(".md")).toList
def listFolders(file: File): Seq[File]   = file.listFiles().filter(_.isDirectory()).toList

def eventListing() = Def.task {
  val file   = (Compile / sourceManaged).value / "io" / "scala" / "data" / "EventFilesName.scala"
  val events = listMarkdowns(new File("./public/scalafr-meetups"))
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

def confListing() = Def.task {
  val file = (Compile / sourceManaged).value / "io" / "scala" / "data" / "ConfFilesName.scala"
  val conferences = listFolders(new File("./public/conferences")).map { folder =>
    val talks = listMarkdowns(folder).filter(_.getName != "conference.md")
    s"""|  val ${folder.getName()} = List(${talks.map(file => "\"" * 3 + IO.read(file) + "\"" * 3).mkString(",")})"""
  }

  val content =
    s"""|package io.scala.data
        |
        |object ConfFilesName {
        ${conferences.mkString("\n")}
        |}""".stripMargin

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
