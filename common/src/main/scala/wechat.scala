package miniprogram

import scala.scalajs.js
import scala.scalajs.js.annotation._
import scala.concurrent.{Future,Promise}
import scala.language.implicitConversions
import cats.effect.{IO}

import js.Dynamic.literal

@JSExportTopLevel("Wechat")
object Wechat {
  type Callback = () => Unit 
  type ErrorCallback = (Throwable) => Unit
  type WechatError = js.JavaScriptException
  implicit val callback: Callback = () => {}
  implicit val errorCallback: ErrorCallback = (e: Throwable) => { println(e) }
  implicit def makeCallback(c: =>Unit): Callback = () => c

  def setData(d: js.Dynamic)(implicit f: Callback): IO[Unit] = {
    IO.pure()
  }
}

object Main {
  def main(args: Array[String]): Unit = {
    import pages._
    module.exports.index =
      literal(
        "onLoad" -> index.onLoad _,
        "onShow" -> index.onShow _,
        "getUserInfo" -> index.getUser _,
      )
  }
}
