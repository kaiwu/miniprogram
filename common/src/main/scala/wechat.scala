package miniprogram

import scala.scalajs.js
import scala.scalajs.js.annotation._
import scala.concurrent.{Future,Promise}
import scala.language.implicitConversions

import zio.{ZIO,IO,UIO,Task}
import js.Dynamic.literal

@JSExportTopLevel("Wechat")
object Wechat {
  type Callback = () => Unit 
  type ErrorCallback = (Throwable) => Unit
  type WechatError = js.JavaScriptException
  implicit val callback: Callback = () => {}
  implicit val errorCallback: ErrorCallback = (e: Throwable) => { println(e) }
  implicit def makeCallback(c: =>Unit): Callback = () => c

  def setData(d: js.Dynamic)(implicit f: Callback): Task[Unit] =
    ZIO.effect[Unit] {
      val current = WXGlobal.getCurrentPages().last
      current.setData(d,f)
    }

  def request(method: String,url: String,header: js.Dynamic,data: js.Dynamic)(implicit cb: Callback): IO[WechatError, js.Dynamic] =
    IO.effectAsync[WechatError, js.Dynamic] { callback => {
        val scb = (ret: js.Dynamic) => callback(IO.succeed(ret))
        val fcb = (err: js.Dynamic) => callback(IO.fail(js.JavaScriptException(err)))
        wx.request(literal(url = url,data = data,header = header,method = method,success = scb,fail = fcb,complete = cb))
      }
    }

  def saveFile(tempFilePath: String)(implicit cb: Callback): IO[WechatError, js.Dynamic] =
    IO.effectAsync[WechatError, js.Dynamic] { callback => {
        val scb = (ret: js.Dynamic) => callback(IO.succeed(ret))
        val fcb = (err: js.Dynamic) => callback(IO.fail(js.JavaScriptException(err)))
        wx.saveFile(literal(tempFilePath = tempFilePath, success = scb, fail = fcb, complete = cb))
      }
    }

  def openDocument(filePath: String, fileType: String)(implicit cb: Callback): IO[WechatError, Unit] =
    IO.effectAsync[WechatError, Unit] { callback => {
        val scb = () => callback(IO.succeed(()))
        val fcb = (err: js.Dynamic) => callback(IO.fail(js.JavaScriptException(err)))
        wx.openDocument(literal(filePath = filePath, fileType = fileType, success = scb, fail = fcb, complete = cb))
      }
    }

  def setStorage(key: String, value: js.Dynamic)(implicit cb: Callback): IO[WechatError, Unit] =
    IO.effectAsync[WechatError, Unit] { callback => {
        val scb = () => callback(IO.succeed(()))
        val fcb = (err: js.Dynamic) => callback(IO.fail(js.JavaScriptException(err)))
        wx.setStorage(literal(key = key, data = value, success = scb, fail = fcb, complete = cb))
      }
    }

  def getStorage(key: String)(implicit cb: Callback): IO[WechatError, js.Dynamic] =
    IO.effectAsync[WechatError, js.Dynamic] { callback => {
        val scb = (ret: js.Dynamic) => callback(IO.succeed(ret))
        val fcb = (err: js.Dynamic) => callback(IO.fail(js.JavaScriptException(err)))
        wx.getStorage(literal(key = key, success = scb, fail = fcb, complete = cb))
      }
    }

  def removeStorage(key: String)(implicit cb: Callback): IO[WechatError, js.Dynamic] =
    IO.effectAsync[WechatError, js.Dynamic] { callback => {
        val scb = (ret: js.Dynamic) => callback(IO.succeed(ret))
        val fcb = (err: js.Dynamic) => callback(IO.fail(js.JavaScriptException(err)))
        wx.removeStorage(literal(key = key, success = scb, fail = fcb, complete = cb))
      }
    }

  def login(implicit cb: Callback): IO[WechatError, js.Dynamic] =
    IO.effectAsync[WechatError, js.Dynamic] { callback => {
        val scb = (ret: js.Dynamic) => callback(IO.succeed(ret))
        val fcb = (err: js.Dynamic) => callback(IO.fail(js.JavaScriptException(err)))
        wx.login(literal(success = scb, fail = fcb, complete = cb))
      }
    }

  def getSystemInfo(implicit cb: Callback): IO[WechatError, js.Dynamic] =
    IO.effectAsync[WechatError, js.Dynamic] { callback => {
        val scb = (ret: js.Dynamic) => callback(IO.succeed(ret))
        val fcb = (err: js.Dynamic) => callback(IO.fail(js.JavaScriptException(err)))
        wx.getSystemInfo(literal(success = scb, fail = fcb, complete = cb))
      }
    }

  def getUserInfo(withCredentials: Boolean, lang: String)(implicit cb: Callback): IO[WechatError, js.Dynamic] =
    IO.effectAsync[WechatError, js.Dynamic] { callback => {
        val scb = (ret: js.Dynamic) => callback(IO.succeed(ret))
        val fcb = (err: js.Dynamic) => callback(IO.fail(js.JavaScriptException(err)))
        wx.getUserInfo(literal(withCredentials = withCredentials, lang = lang, success = scb, fail = fcb, complete = cb))
      }
    }

  def getSetting(withSubscriptions: Boolean)(implicit cb: Callback): IO[WechatError, js.Dynamic] =
    IO.effectAsync[WechatError, js.Dynamic] { callback => {
        val scb = (ret: js.Dynamic) => callback(IO.succeed(ret))
        val fcb = (err: js.Dynamic) => callback(IO.fail(js.JavaScriptException(err)))
        wx.getSetting(literal(withSubscriptions = withSubscriptions, success = scb, fail = fcb, complete = cb))
      }
    }

  def checkSession(implicit cb: Callback): IO[WechatError, js.Dynamic] =
    IO.effectAsync[WechatError, js.Dynamic] { callback => {
        val scb = (ret: js.Dynamic) => callback(IO.succeed(ret))
        val fcb = (err: js.Dynamic) => callback(IO.fail(js.JavaScriptException(err)))
        wx.checkSession(literal(success = scb, fail = fcb, complete = cb))
      }
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
