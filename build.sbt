import org.scalajs.linker.interface.ModuleSplitStyle.SmallestModules

ThisBuild / version       := "0.1.0"
ThisBuild / scalaVersion  := "3.3.1"
ThisBuild / versionScheme := Some("early-semver")


val publicFolderDev  = taskKey[String]("Returns the compiled main.js parent path for dev")
val publicFolderProd = taskKey[String]("Returns the compiled main.js parent path for production`")
val currentYear = settingKey[String]("Current year (dev only)")

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
      "org.foundweekends" %%% "knockoff"    % "0.9.0"
    ),
    scalaJSUseMainModuleInitializer := true,
    scalaJSLinkerConfig ~= {
      _.withModuleKind(ModuleKind.ESModule)
        .withModuleSplitStyle(SmallestModules)
        .withSourceMap(false)
    },
    publicFolderDev  := linkerOutputDirectory((Compile / fastLinkJS).value).getAbsolutePath,
    publicFolderProd := linkerOutputDirectory((Compile / fullLinkJS).value).getAbsolutePath,
    Compile / currentYear := "2024",
    Compile / sourceGenerators += talksSourceGenerator.taskValue,
    Compile / sourceGenerators += sponsorsSourceGenerator.taskValue
  )

def linkerOutputDirectory(v: Attributed[org.scalajs.linker.interface.Report]): File = {
  v.get(scalaJSLinkerOutputDirectory.key).getOrElse {
    throw new MessageOnlyException(
      "Linking report was not attributed with output directory. " +
        "Please report this as a Scala.js bug."
    )
  }
}

def lines(lines: String*)               = lines.mkString("\n")
def slugify(name: String): String       = name.toLowerCase().replace("-", "_")
def listMarkdown(file: File): Seq[File] = file.listFiles().filter(_.getName.endsWith(".md")).toList

// TODO: on prod env, generate all sources. On dev env generate source from current year only (can use a build setting key)
def talksSourceGenerator = Def.task {
  val outputFile = (Compile / sourceManaged).value / "io" / "scala" / "data" / "Talks.scala"
  val markdowns: Seq[File] =
    listMarkdown((Compile / resourceDirectory).value / "talks" / (Compile / currentYear).value)

  val content: String =
    s"""
      |package io.scala.data
      |object Talks {
      |${lines(markdowns.map(file => {
        val name: String    = slugify(file.getName.stripSuffix(".md"))
        val content: String = "\"" * 3 + IO.read(file) + "\"" * 3

        s"  val $name = $content"
      })*)}
      |}""".stripMargin

  if (!outputFile.exists() || IO.read(outputFile) != content) {
    IO.write(outputFile, content)
  }

  Seq(outputFile)
}

//TODO: traverse the sponsors/YYYY.md file and extract the 2nd level title for value's name and list for value's value. A line iterator should allow to get the first line without computing the others
def sponsorsSourceGenerator = Def.task {
  val outputFile = (Compile / sourceManaged).value / "io" / "scala" / "data" / "Sponsors.scala"
  val markdowns: Seq[File] =
    listMarkdown((Compile / resourceDirectory).value / "sponsors").filter(_.name.startsWith((Compile / currentYear).value))
  val content: String =
    s"""
      |package io.scala.data
      |
      |import io.scala.domaines.Sponsor
      |
      |object Sponsors {
      |
      | val sponsorsMd = List(
      |${lines(markdowns.map(file => {
        val content: String = IO.read(file)
        content
          .split("##")
          .drop(1)
          .map { sponsor =>
            val it = sponsor.linesIterator
            it.next()
            s"  ${"\"" * 3}${it.mkString("\n").trim()}${"\"" * 3}"
          }
          .mkString(",\n")
      })*)}
      |  )
      |}""".stripMargin

  if (!outputFile.exists() || IO.read(outputFile) != content) {
    IO.write(outputFile, content)
  }

  Seq(outputFile)
}
