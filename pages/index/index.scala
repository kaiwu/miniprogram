import scala.scalajs.js
import js.Dynamic.literal
import miniprogram._

object Main {
  val common = WXGlobal.require("../../common.js")
  val page = literal(
    onLoad = (query: js.Dynamic) => common.index.onLoad(query),
    onShow = () => common.index.onShow()
  )

  def main(args: Array[String]): Unit = {
    Page(page)
  }
}
