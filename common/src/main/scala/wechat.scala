package miniprogram

import scala.scalajs.js
import js.Dynamic.literal
import scala.scalajs.js.annotation._
import zio.{ZIO,UIO,Task}

@JSExportTopLevel("Wechat")
object Wechat {
  type Callback = () => Unit 
  type ErrorCallback = (Throwable) => Unit
  implicit val callback: Callback = () => {}
  implicit val errorCallback: ErrorCallback = (e: Throwable) => { println(e) }

  def setData(o: js.Dynamic,f: Callback = callback): Unit = {
    val current = WXGlobal.getCurrentPages().last
    current.setData(o,f)
  }
}

object Main {
  def main(args: Array[String]): Unit = {
    import pages._
    module.exports.index =
      literal(
        "onLoad" -> index.onLoad _,
        "onShow" -> index.onShow _,
      )
  }
}
