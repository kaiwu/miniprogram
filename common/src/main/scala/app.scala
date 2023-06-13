package miniprogram

import scala.scalajs.js
import js.Dynamic.literal
import miniprogram._
import cats.effect.IO
import cats.effect.unsafe.implicits.global

object WechatApp {
    def onLaunch(info: js.Dynamic): Unit = {
      println("App Launch: " + js.JSON.stringify(info))
      js.Dynamic.global.Promise.resolved = js.Dynamic.global.Promise.resolve
    }

    def onShow(info: js.Dynamic): Unit = {
      println("App Show: " + js.JSON.stringify(info))
      import Wechat.callback
      val login = for {
        f1 <- Wechat.login
        _  <- IO.println(s"code is ${f1.code}")
        _  <- Wechat.setStorage("code", f1.code)
      } yield ()

      login.unsafeRunAsync(callback => {})
    }
}

