name := "Scala.js miniprogram"
scalaVersion := "2.13.4"

// libraryDependencies += "dev.zio" %%% "zio" % "1.0.3"
// libraryDependencies += "dev.zio" %%% "zio-test" % "1.0.3" % "test"
// libraryDependencies += "dev.zio" %%% "zio-test-sbt" % "1.0.3" % "test"
// testFrameworks += new TestFramework("zio.test.sbt.ZTestFramework")

import  com.typesafe.sbt.web._
import  Import.WebKeys._

lazy val root = (project in file(".")).aggregate(common, app, images, index)
lazy val facede = (project in file("facede")).enablePlugins(ScalaJSPlugin)

lazy val common = (project in file("common")).enablePlugins(ScalaJSPlugin)
    .dependsOn(facede)
    .settings(
      scalaJSUseMainModuleInitializer := true,
      libraryDependencies ++= Seq("dev.zio" %%% "zio" % "1.0.3"),
      webTarget := target.value / ".." / ".." / "target",
      artifactPath in fullOptJS in Compile := webTarget.value / (name.value + ".js")
    )
                  
lazy val commonSettings = Seq(
    sourceDirectory in Assets := baseDirectory.value,
    public in Assets := webTarget.value,
    resourceManaged in (Assets, LessKeys.less) := webTarget.value,
    mappings in Assets := (mappings in sources in Assets).value,
    includeFilter in Assets := new SimpleFileFilter(_.getParent == baseDirectory.value + "/lib") || "*.wxml" || "*.json" ,
    includeFilter in (Assets, LessKeys.less) := "*.less",
    excludeFilter in (Assets, LessKeys.less) := "*.wxss",
    managedSources in (Assets, LessKeys.less) := (sourceDirectory in Assets).value.descendantsExcept((includeFilter in (Assets, LessKeys.less)).value, excludeFilter.value).get,
    LessKeys.sourceMap in Assets := false,
    LessKeys.compress in Assets := true,
    scalaJSUseMainModuleInitializer := true,
    webTarget := target.value / ".." / ".." / ".." / "target" / "pages" / name.value,
    artifactPath in fullOptJS in Compile := webTarget.value / (name.value + ".js")
)                

lazy val componentSettings = Seq(
    sourceDirectory in Assets := baseDirectory.value,
    public in Assets := webTarget.value,
    includeFilter in Assets := "*.js" | "*.json" | "*.wxss" | "*.svg" | "*.wxml" | "*.jpg" | "*.png",
    webTarget := target.value / ".." / ".." / ".." / "target" / "components" / name.value
)

lazy val app  = (project in file("app"))
    .enablePlugins(SbtWeb,ScalaJSPlugin,BuildInfoPlugin,GitVersioning)
    .dependsOn(facede)
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
      sourceDirectory in Assets := baseDirectory.value,
      public in Assets := webTarget.value,
      includeFilter in Assets := "*.png" | "*.jpg",
      webTarget := target.value / ".." / ".." / "target" / name.value
    )

lazy val index  = (project in file("pages/index")).enablePlugins(SbtWeb,ScalaJSPlugin).dependsOn(facede).settings(commonSettings)

