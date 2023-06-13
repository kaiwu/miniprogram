import scala.scalajs.js
import js.Dynamic.literal
import build.BuildInfo
import miniprogram._

object Main {
  val globalData = js.Dictionary[js.Dynamic]()
  val app = literal(
    set = (d: js.Dynamic) => globalData ++= d.asInstanceOf[js.Dictionary[js.Dynamic]],
    get = (k: String) => globalData(k),
    onLaunch = (info: js.Dynamic) => {
      println("App Launch: " + js.JSON.stringify(info))
      js.Dynamic.global.Promise.resolved = js.Dynamic.global.Promise.resolve
    },
    onShow = (info: js.Dynamic) => {
      println("App Show: " + js.JSON.stringify(info))
    },
    getWxHost = () => BuildInfo.wxrequest,
    getWxCdn = () => BuildInfo.wxdownload,
    getRevision = () => s"v${BuildInfo.version} ${BuildInfo.gitHeadCommit.get.substring(0,10)}",
  )
  def main(args: Array[String]): Unit = {
    App(app)
  }
}
