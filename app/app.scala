import scala.scalajs.js
import js.Dynamic.literal
import build.BuildInfo
import miniprogram._

object Main {
  val globalData = js.Dictionary[js.Dynamic]()
  val common = WXGlobal.require("./common.js")
  val app = literal(
    set = (d: js.Dynamic) => globalData ++= d.asInstanceOf[js.Dictionary[js.Dynamic]],
    get = (k: String) => globalData(k),
    onLaunch = (info: js.Dynamic) => common.app.onLaunch(info),
    onShow = (info: js.Dynamic) => common.app.onShow(info),
    getWxHost = () => BuildInfo.wxrequest,
    getWxCdn = () => BuildInfo.wxdownload,
    getRevision = () => s"v${BuildInfo.version} ${BuildInfo.gitHeadCommit.get.substring(0,10)}",
  )
  def main(args: Array[String]): Unit = {
    App(app)
  }
}
