import org.scalajs.linker.interface.ModuleSplitStyle.SmallestModules
import TalkParser

ThisBuild / version       := "0.1.0"
ThisBuild / scalaVersion  := "3.3.1"
ThisBuild / versionScheme := Some("early-semver")

val publicFolderDev  = taskKey[String]("Returns the compiled main.js parent path for dev")
val publicFolderProd = taskKey[String]("Returns the compiled main.js parent path for production`")
val generateSource   = taskKey[Unit]("Generate sources from markdowns")

lazy val root = project
  .in(file("."))
  .enablePlugins(ScalaJSPlugin)
  .settings(
    name := "scalaio-website",
    // scalacOptions += "-Yexplicit-nulls",
    libraryDependencies ++= Seq(
      "com.raquo"         %%% "laminar"     % Dependencies.laminar,
      "com.raquo"         %%% "waypoint"    % Dependencies.waypoint,
      "com.lihaoyi"       %%% "upickle"     % Dependencies.upickle,
      "org.scala-js"      %%% "scalajs-dom" % "2.4.0",
      "org.foundweekends" %%% "knockoff"    % "0.9.0",
      "org.typelevel"     %%% "fabric-core" % "1.14.3"
    ),
    scalaJSUseMainModuleInitializer := true,
    scalaJSLinkerConfig ~= {
      _.withModuleKind(ModuleKind.ESModule)
        .withModuleSplitStyle(SmallestModules)
        .withSourceMap(false)
    },
    publicFolderDev  := linkerOutputDirectory((Compile / fastLinkJS).value).getAbsolutePath,
    publicFolderProd := linkerOutputDirectory((Compile / fullLinkJS).value).getAbsolutePath,
    Compile / sourceGenerators := List(
      talksGenerator.taskValue,
      sponsorsGenerator.taskValue,
      speakersGenerator.taskValue
    )
  )

def linkerOutputDirectory(v: Attributed[org.scalajs.linker.interface.Report]): File = {
  v.get(scalaJSLinkerOutputDirectory.key).getOrElse {
    throw new MessageOnlyException(
      "Linking report was not attributed with output directory. " +
        "Please report this as a Scala.js bug."
    )
  }
}

def slugify(name: String): String       = name.toLowerCase().replace("-", "_")
def listMarkdown(file: File): Seq[File] = file.listFiles().filter(_.getName.endsWith(".md")).toList
def listFolder(file: File): Seq[File]   = file.listFiles().filter(_.isDirectory()).toList
def capitalize(name: String): String    = name.split("-").map(_.capitalize).mkString
def toCamelCase(name: String): String = {
  val elements = name.split("-")
  elements.head + elements.tail.map(capitalize).mkString
}

def managedSource(name: String, outputFile: File, markdowns: Seq[File], f: File => (String, String)): File = {
  val (valNames, contents) = markdowns.map(f).unzip
  val content: String =
    s"""
      |package io.scala.data
      |object $name {
      |  ${contents.mkString("\n  ")}
      |
      |  val all$name = List(${valNames.mkString(", ")})
      |}
      |""".stripMargin

  if (!outputFile.exists() || IO.read(outputFile) != content) {
    IO.write(outputFile, content)
  }

  outputFile
}

// TODO: on prod env, generate all sources. On dev env generate source from current year only (can use a build setting key)
def talksGenerator = Def.task {
  listFolder((Compile / resourceDirectory).value / "talks")
    .map { folder =>
      val name = s"Talks${capitalize(folder.name)}"
      managedSource(
        name,
        (Compile / sourceManaged).value / "io" / "scala" / "data" / s"$name.scala",
        listMarkdown(folder),
        file => {
          val name: String    = toCamelCase(file.base)
          val content: String = "\"" * 3 + IO.read(file) + "\"" * 3

          (name, s"val $name = $content")
        }
      )
    }
}

def speakersGenerator = Def.task {
  listFolder((Compile / resourceDirectory).value / "speakers")
    .map { folder =>
      val name = s"Speakers${capitalize(folder.name)}"
      managedSource(
        name,
        (Compile / sourceManaged).value / "io" / "scala" / "data" / s"$name.scala",
        listMarkdown(folder),
        file => {
          val name: String = toCamelCase(file.base)
          val content: String =
            s"""
            |  ${"\"" * 3}
            |  ${IO.readLines(file).mkString("  ||", "\n  |||", "")}
            |  ${"\"" * 3}""".stripMargin

          (name, s"val $name = $content.stripMargin")
        }
      )
    }
}

def sponsorsGenerator = Def.task {
  listMarkdown((Compile / resourceDirectory).value / "sponsors")
    .map { md =>
      val name = s"Sponsors${capitalize(md.base)}"
      managedSource(
        name,
        (Compile / sourceManaged).value / "io" / "scala" / "data" / s"$name.scala",
        Seq(md),
        file => {
          val content: String =
            IO.read(file)
              .split("##")
              .drop(1)
              .map { sponsor =>
                val it = sponsor.linesIterator
                it.next()
                s"  ${"\"" * 3}${it.mkString("\n").trim()}${"\"" * 3}"
              }
              .mkString(",\n")
          ("", s"val sponsors = List(\n$content\n)")
        }
      )
    }
}
