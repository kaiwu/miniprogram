name := "Scala.js miniprogram"
scalaVersion := "3.2.0"
scalacOptions += "-deprecation"
scalacOptions += "-feature"

import  com.typesafe.sbt.web._
import  Import.WebKeys._

lazy val root = (project in file(".")).aggregate(common, app, images, index)
lazy val facade = (project in file("facade")).enablePlugins(ScalaJSPlugin)

lazy val common = (project in file("common")).enablePlugins(ScalaJSPlugin)
    .dependsOn(facade)
    .settings(
      scalaJSUseMainModuleInitializer := true,
      libraryDependencies ++= Seq(
      "io.github.cquiroz" %%% "scala-java-time" % "2.2.0",
      "io.github.cquiroz" %%% "scala-java-time-tzdb" % "2.2.0",
      "dev.zio" %%% "zio" % "1.0.17"),
      webTarget := target.value / ".." / ".." / "target",
      Compile / fullOptJS / artifactPath := webTarget.value / (name.value + ".js")
    )
                  
lazy val commonSettings = Seq(
    Assets / sourceDirectory := baseDirectory.value,
    Assets / public := webTarget.value,
    Assets / LessKeys.less / resourceManaged := webTarget.value,
    Assets / mappings := (Assets / sources / mappings).value,
    Assets / includeFilter := new SimpleFileFilter(_.getParent == baseDirectory.value + "/lib") || "*.wxml" || "*.json" ,
    Assets / LessKeys.less / includeFilter := "*.less",
    Assets / LessKeys.less / excludeFilter := new SimpleFileFilter(_.getAbsolutePath.contains("style")) || "*.wxss",
    Assets / LessKeys.less / managedSources := (Assets / sourceDirectory).value.descendantsExcept((Assets / LessKeys.less / includeFilter).value, excludeFilter.value).get,
    Assets / LessKeys.sourceMap := false,
    Assets / LessKeys.compress := true,
    scalaJSUseMainModuleInitializer := true,
    webTarget := target.value / ".." / ".." / ".." / "target" / "pages" / name.value,
    Compile / fullOptJS / artifactPath := webTarget.value / (name.value + ".js")
)                

lazy val componentSettings = Seq(
    Assets / sourceDirectory := baseDirectory.value,
    Assets / public := webTarget.value,
    Assets / includeFilter := "*.js" | "*.json" | "*.wxss" | "*.svg" | "*.wxml" | "*.jpg" | "*.png",
    webTarget := target.value / ".." / ".." / ".." / "target" / "components" / name.value
)

lazy val app  = (project in file("app"))
    .enablePlugins(SbtWeb,ScalaJSPlugin,BuildInfoPlugin,GitVersioning)
    .dependsOn(facade)
    .settings(
      commonSettings,
      webTarget := target.value / ".." / ".." / "target",
      version := "1.0.0",
      buildInfoKeys ++= Seq[BuildInfoKey](
        git.gitHeadCommit,
        "wxrequest" -> sys.env.get("WX_REQUEST").getOrElse("weapp.darkanchor.com"),
        "wxdownload" -> sys.env.get("WX_DOWNLOAD").getOrElse("cdn.darkanchor.com")
      ),
      buildInfoPackage := "build"
    )

lazy val images = (project in file("images"))
    .enablePlugins(SbtWeb)
    .settings(
      Assets / sourceDirectory := baseDirectory.value,
      Assets / public := webTarget.value,
      Assets / includeFilter := "*.png" | "*.jpg",
      webTarget := target.value / ".." / ".." / "target" / name.value
    )

lazy val index  = (project in file("pages/index")).enablePlugins(SbtWeb,ScalaJSPlugin).dependsOn(facade).settings(commonSettings)

