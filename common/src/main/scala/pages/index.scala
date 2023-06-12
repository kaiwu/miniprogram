package pages

import scala.scalajs.js
import js.Dynamic.literal
import miniprogram.Wechat._

object index {
  def onLoad(query: js.Dynamic): Unit = {
  }

  def onShow(): Unit = {
    // runtime.unsafeRun(ZIO(println("onShow!")))
  }

  def getUser(e: js.Dynamic): Unit = {
    // e.detail.errMsg = "getUserInfo:fail auth deny"
    // e.detail.errMsg = "getUserInfo:ok"
  }
}

