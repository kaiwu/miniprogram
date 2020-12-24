package pages

import scala.scalajs.js
import js.Dynamic.literal
import zio.{ZIO,Runtime}
import miniprogram.Wechat._

object index {
  val runtime = Runtime.default
  def onLoad(query: js.Dynamic) = {
    runtime.unsafeRun(ZIO(println("onLoad!")))
    setData(literal("label" -> "on load from common"))
  }

  def onShow() = {
    runtime.unsafeRun(ZIO(println("onShow!")))
  }
}

