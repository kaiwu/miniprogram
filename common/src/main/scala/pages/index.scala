package pages

import scala.scalajs.js
import js.Dynamic.literal
import miniprogram._

object index {
  def onLoad(query: js.Dynamic): Unit = {
    println("index onLoad: " + WXGlobal.getApp().getWxHost())
  }

  def onShow(): Unit = {
    println("index onShow: " + WXGlobal.getApp().getRevision())
  }

  def getUser(e: js.Dynamic): Unit = {
    println(js.JSON.stringify(e))
  }
}

