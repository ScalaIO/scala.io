import java.io.File
import org.scalajs.linker.interface.ModuleSplitStyle.SmallestModules

ThisBuild / version       := "0.1.0"
ThisBuild / scalaVersion  := "3.5.0"
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
      "-Yexplicit-nulls",
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
      "io.github.cquiroz" %%% "scala-java-time" % "2.5.0", // implementations of java.time classes for Scala.JS
      "org.scalactic"     %%% "scalactic"       % "3.2.19",
      "org.scalatest"     %%% "scalatest"       % "3.2.19" % "test"
    ),
    scalaJSUseMainModuleInitializer := true,
    scalaJSLinkerConfig ~= {
      _.withModuleKind(ModuleKind.ESModule)
        .withModuleSplitStyle(SmallestModules)
        .withSourceMap(false)
    },
    confLister       := sessionsListing().value,
    publicFolderDev  := linkerOutputDirectory((Compile / fastLinkJS).value).getAbsolutePath,
    publicFolderProd := linkerOutputDirectory((Compile / fullLinkJS).value).getAbsolutePath,
    Compile / sourceGenerators ++= Seq(
      eventListing().taskValue,
      sessionsListing().taskValue,
      sponsorListing().taskValue,
      confListing().taskValue
    )
  )

def slugify(name: String): String        = name.toLowerCase().replace("-", "_")
val allConferences                       = new File("./public/conferences")

def eventListing() = Def.task {
  val file   = (Compile / sourceManaged).value / "io" / "scala" / "data" / "EventsData.scala"
  val events = allConferences.listFiles(_.name.endsWith(".md"))
  val content =
    s"""|package io.scala.data
       |
       |object EventsData:
       |  val events = List(${events.map(file => "\"" * 3 + IO.read(file) + "\"" * 3).mkString(",")})
       |""".stripMargin

  if (!file.exists() || IO.read(file) != content) {
    IO.write(file, content)
  }
  Seq(file)
}

def confListing() = Def.task {
  val file = (Compile / sourceManaged).value / "io" / "scala" / "data" / "ConfsData.scala"
  val conferences = allConferences.globRecursive(_.name == "conference.md").get().map { file =>
    val folderSlug = slugify(file.getParentFile().name)
    folderSlug -> s"""|  def ${folderSlug}: String = ${"\"" * 3 + IO.read(file) + "\"" * 3}"""
  }

  val content =
    s"""|package io.scala.data
        |
        |object ConfsData {
        ${conferences.map(_._2).mkString("\n")}
        |  def all: Seq[String] = Seq(${conferences.map(x => s""""${x._1}"""").mkString(",")})
        |}""".stripMargin

  if (!file.exists() || IO.read(file) != content) {
    IO.write(file, content)
  }
  Seq(file)
}

def sessionsListing() = Def.task {
  val file = (Compile / sourceManaged).value / "io" / "scala" / "data" / "SessionsData.scala"
  val sessionsByConf = allConferences.listFiles(_.isDirectory()).map { folder =>
    val sessions = (folder / "sessions").listFiles()
    val slug     = slugify(folder.getName)
    s"""|  def $slug: List[String] = List(${sessions
        .map(file => "\"" * 3 + IO.read(file) + "\"" * 3)
        .mkString(",")})"""
  }

  val content =
    s"""|package io.scala.data
        |
        |object SessionsData {
        ${sessionsByConf.mkString("\n")}
        |}""".stripMargin

  if (!file.exists() || IO.read(file) != content) {
    IO.write(file, content)
  }
  Seq(file)
}

def sponsorListing() = Def.task {
  val file = (Compile / sourceManaged).value / "io" / "scala" / "data" / "SponsorsData.scala"
  val sponsors =
    allConferences.globRecursive(_.name == "sponsors.md").get().map { md =>
      s"""|  def ${slugify(md.getParentFile.name)}: String = ${"\"" * 3 + IO.read(md) + "\"" * 3}"""
    }

  val content =
    s"""|package io.scala.data
        |
        |object SponsorsData {
        ${sponsors.mkString("\n")}
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
