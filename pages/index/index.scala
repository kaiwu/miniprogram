import scala.scalajs.js
import js.Dynamic.literal
import miniprogram._

object Main {
  val common = WXGlobal.require("../../common.js")
  val page = literal(
    onLoad = (query: js.Dynamic) => {
      common.setData(literal("label" -> "ZIO Scala.js"))
    }
  )

  def main(args: Array[String]): Unit = {
    Page(page)
  }
}
