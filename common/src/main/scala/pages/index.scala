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

    val login = for {
      f1 <- Wechat.login{}
      _  <- IO.println(f1.code)
      _  <- Wechat.setStorage("code", f1.code){}
    } yield ()

    login.unsafeRunAsync(callback => {})
  }

  def getUser(e: js.Dynamic): Unit = {
    println(js.JSON.stringify(e))
  }
}

