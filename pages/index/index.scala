import scala.scalajs.js
import js.Dynamic.literal
import miniprogram._

object Main {
  val common = WXGlobal.require("../../common.js")
  val page = literal(
    data = literal(canIUse = wx.canIUse("button.open-type.getUserInfo")),
    onLoad = (query: js.Dynamic) => common.index.onLoad(query),
    onShow = () => common.index.onShow(),
    getUserInfo = (e: js.Dynamic) => common.index.getUserInfo(e)
  )

  def main(args: Array[String]): Unit = {
    Page(page)
  }
}
