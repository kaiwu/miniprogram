package pages

import scala.scalajs.js
import js.Dynamic.literal
import miniprogram._

object index {
  def onLoad(query: js.Dynamic): Unit = {
    WXGlobal.getApp().set(literal(
      label = "welcome to scalajs",
      fortytwo = 42
    ))
  }

  def onShow(): Unit = {
    val app = WXGlobal.getApp()
    println(s"index onShow: ${app.getRevision()}")
    println(s"forty two is: ${app.get("fortytwo")}, ${app.get("label")}")
  }

  def getUser(e: js.Dynamic): Unit = {
    println(js.JSON.stringify(e))
  }
}

