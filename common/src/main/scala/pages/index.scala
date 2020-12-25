package pages

import zio._
import scala.scalajs.js
import js.Dynamic.literal
import miniprogram.Wechat._

object index {
  val runtime = Runtime.default
  val load = for {
    settings <- getSetting(false) map {_.authSetting}
    _        <- console.putStrLn(js.JSON.stringify(settings))
    if settings.asInstanceOf[js.Dictionary[Boolean]].get("scope.userInfo") == Some(true)
    userinfo <- getUserInfo(false, "zh_CN") map {_.userInfo}
    _        <- setData(userinfo)
  } yield ()

  def onLoad(query: js.Dynamic): Unit = {
  }

  def onShow(): Unit = {
    // runtime.unsafeRun(ZIO(println("onShow!")))
  }

  def getUser(e: js.Dynamic): Unit = {
    // e.detail.errMsg = "getUserInfo:fail auth deny"
    // e.detail.errMsg = "getUserInfo:ok"
    runtime.unsafeRunAsync(load)(_ => println("DONE"))
  }
}

