package pages

import miniprogram._
import scala.scalajs.js
import js.Dynamic.literal
import zio.{ZIO,Runtime}

object index {
  val runtime = Runtime.default
  def onLoad(query: js.Dynamic) = {
    runtime.unsafeRun(ZIO(println("onLoad!")))
    Wechat.setData(literal("label" -> "on load from common"))
  }

  def onShow() = {
    runtime.unsafeRun(ZIO(println("onShow!")))
  }
}

