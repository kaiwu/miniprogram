package pages

import scala.scalajs.js
import js.Dynamic.literal
import miniprogram._
import cats.effect.IO
import cats.effect.unsafe.implicits.global

object index {
  def onLoad(query: js.Dynamic): Unit = {
      WXGlobal.getApp().set(literal(
        appid = "scalajs",
        fortytwo = 42
      ))
  }

  def onShow(): Unit = {
    val app = WXGlobal.getApp()
    println(s"index onShow: ${app.getRevision()}")
    println(s"forty two is: ${app.get("fortytwo")}")
  }

  def onReady(): Unit = {
    // this can happen *BEFORE* setStorage
    import Wechat.callback
    val nick = for {
      f1 <- Wechat.getStorage("code")
      _  <- IO.println(js.JSON.stringify(f1))
      _  <- Wechat.setData(literal(nickName = f1))
    } yield ()

    nick.unsafeRunAsync(callback => {})
  }

  def getUser(e: js.Dynamic): Unit = {
    println(js.JSON.stringify(e))
  }
}

