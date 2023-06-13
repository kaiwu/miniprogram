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
  implicit val callback: Callback = () => {}
  implicit val errorCallback: ErrorCallback = (e: Throwable) => { println(e) }
  implicit def makeCallback(c: =>Unit): Callback = () => c

  def setData(d: js.Dynamic)(implicit f: Callback): IO[Unit] =
    IO({
      val page = WXGlobal.getCurrentPages().last
      page.setData(d, f)
    })

  def login(implicit cb: Callback): IO[js.Dynamic] =
    IO.async(callback => {
        val scb = (ret: js.Dynamic) => callback(Right(ret))
        val fcb = (err: js.Dynamic) => callback(Left(js.JavaScriptException(err)))
        wx.login(literal(success = scb, fail = fcb, complete = cb))
        IO.none
    })

  def request(method: String,url: String,header: js.Dynamic,data: js.Dynamic)(implicit cb: Callback): IO[js.Dynamic] =
    IO.async(callback => {
        val scb = (ret: js.Dynamic) => callback(Right(ret))
        val fcb = (err: js.Dynamic) => callback(Left(js.JavaScriptException(err)))
        val _ = wx.request(literal(url = url,data = data,header = header,method = method,success = scb,fail = fcb,complete = cb))
        IO.none
    })

  def setStorage(key: String, value: js.Dynamic)(implicit cb: Callback): IO[Unit] =
    IO.async(callback => {
        val scb = () => callback(Right(()))
        val fcb = (err: js.Dynamic) => callback(Left(js.JavaScriptException(err)))
        wx.setStorage(literal(key = key, data = value, success = scb, fail = fcb, complete = cb))
        IO.none
    })
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
