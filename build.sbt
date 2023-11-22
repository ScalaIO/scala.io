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
    libraryDependencies ++= Seq(
      "com.raquo"   %%% "laminar"  % Dependencies.laminar,
      "com.raquo"   %%% "waypoint" % Dependencies.waypoint,
      "com.lihaoyi" %%% "upickle"  % Dependencies.upickle,
      "org.scala-js" %%% "scalajs-dom" % "2.4.0"
    ),
    scalaJSUseMainModuleInitializer := true,
    scalaJSLinkerConfig ~= {
      _.withModuleKind(ModuleKind.ESModule)
        .withModuleSplitStyle(SmallModulesFor(List("io.scala")))
        .withSourceMap(false)
    },
    publicFolderDev := linkerOutputDirectory((Compile / fastLinkJS).value).getAbsolutePath,
    publicFolderProd := linkerOutputDirectory((Compile / fullLinkJS).value).getAbsolutePath
  )

def linkerOutputDirectory(v: Attributed[org.scalajs.linker.interface.Report]): File = {
  v.get(scalaJSLinkerOutputDirectory.key).getOrElse {
    throw new MessageOnlyException(
      "Linking report was not attributed with output directory. " +
        "Please report this as a Scala.js bug."
    )
  }
}