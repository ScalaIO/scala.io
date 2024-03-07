import org.scalajs.linker.interface.ModuleSplitStyle.SmallestModules

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
    Compile / sourceGenerators += talksSourceGenerator.taskValue
  )

def linkerOutputDirectory(v: Attributed[org.scalajs.linker.interface.Report]): File = {
  v.get(scalaJSLinkerOutputDirectory.key).getOrElse {
    throw new MessageOnlyException(
      "Linking report was not attributed with output directory. " +
        "Please report this as a Scala.js bug."
    )
  }
}

// TODO: on prod env, generate all sources. On dev env generate source from current year only (can use a build setting key)
def talksSourceGenerator = Def.task {
  val file = (Compile / sourceManaged).value / "io" / "scala" / "data" / "Talks.scala"

  val markdowns: Seq[File] =
    ((Compile / resourceDirectory).value / "talks" / "2024").listFiles().filter(_.getName.endsWith(".md")).toList

  def lines(lines: String*)         = lines.mkString("\n")
  def slugify(name: String): String = name.toLowerCase().replace("-", "_")

  val content: String = lines(
    "package io.scala.data",
    "",
    "object Talks {",
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
}

//TODO: traverse the sponsors/YYYY.md file and extract the 2nd level title for value's name and list for value's value. A line iterator should allow to get the first line without computing the others
def sponsorsSourceGenerator = ???
