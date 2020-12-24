package miniprogram

import scala.scalajs.js
import scala.scalajs.js.annotation._
import scala.concurrent.{Future,Promise}

import zio.{ZIO,UIO,Task}
import js.Dynamic.literal

@JSExportTopLevel("Wechat")
object Wechat {
  type Callback = () => Unit 
  type ErrorCallback = (Throwable) => Unit
  implicit val callback: Callback = () => {}
  implicit val errorCallback: ErrorCallback = (e: Throwable) => { println(e) }

  def setData(d: js.Dynamic)(implicit f: Callback): Unit = {
    val current = WXGlobal.getCurrentPages().last
    current.setData(d,f)
  }

  def request(method: String,url: String,header: js.Dynamic,data: js.Dynamic)(implicit cb: Callback): Task[js.Dynamic] = {
    val p = Promise[js.Dynamic]()
    val scb = (ret: js.Dynamic) => p.success(ret)
    val fcb = () => p.failure(js.JavaScriptException("wx.request"))
    val task = wx.request(literal(url = url,data = data,header = header,method = method,success = scb,fail = fcb,complete = cb))
    ZIO.fromPromiseScala(p)
  }

  def saveFile(tempFilePath: String)(implicit cb: Callback): Task[js.Dynamic] = {
    val p = Promise[js.Dynamic]()
    val scb = (ret: js.Dynamic) => p.success(ret)
    val fcb = () => p.failure(js.JavaScriptException("wx.saveFile"))
    wx.saveFile(literal(tempFilePath = tempFilePath, success = scb, fail = fcb, complete = cb))
    ZIO.fromPromiseScala(p)
  }

  def openDocument(filePath: String, fileType: String)(implicit cb: Callback): Task[js.Dynamic] = {
    val p = Promise[js.Dynamic]()
    val scb = () => p.success(literal())
    val fcb = () => p.failure(js.JavaScriptException("wx.openDocument"))
    wx.openDocument(literal(filePath = filePath, fileType = fileType, success = scb, fail = fcb, complete = cb))
    ZIO.fromPromiseScala(p)
  }

  def setStorage(key: String, value: js.Dynamic)(implicit cb: Callback): Task[js.Dynamic] = {
    val p = Promise[js.Dynamic]()
    val scb = () => p.success(literal(key -> value))
    val fcb = () => p.failure(js.JavaScriptException("wx.setStorage"))
    wx.setStorage(literal(key = key, data = value, success = scb, fail = fcb, complete = cb))
    ZIO.fromPromiseScala(p)
  }

  def getStorage(key: String)(implicit cb: Callback): Task[js.Dynamic] = {
    val p = Promise[js.Dynamic]()
    val scb = (ret: js.Dynamic) => p.success(ret.data)
    val fcb = () => p.failure(js.JavaScriptException("wx.getStorage"))
    wx.getStorage(literal(key = key, success = scb, fail = fcb, complete = cb))
    ZIO.fromPromiseScala(p)
  }

  def removeStorage(key: String)(implicit cb: Callback): Task[js.Dynamic] = {
    val p = Promise[js.Dynamic]()
    val scb = (ret: js.Dynamic) => p.success(ret)
    val fcb = () => p.failure(js.JavaScriptException("wx.removeStorage"))
    wx.removeStorage(literal(key = key, success = scb, fail = fcb, complete = cb))
    ZIO.fromPromiseScala(p)
  }

  def login(implicit cb: Callback): Task[js.Dynamic] = {
    val p = Promise[js.Dynamic]()
    val scb = (ret: js.Dynamic) => p.success(ret)
    val fcb = () => p.failure(js.JavaScriptException("wx.login"))
    wx.login(literal(success = scb, fail = fcb, complete = cb))
    ZIO.fromPromiseScala(p)
  }

  def getSystemInfo(implicit cb: Callback): Task[js.Dynamic] = {
    val p = Promise[js.Dynamic]()
    val scb = (ret: js.Dynamic) => p.success(ret)
    val fcb = () => p.failure(js.JavaScriptException("wx.getSystemInfo"))
    wx.getSystemInfo(literal(success = scb, fail = fcb, complete = cb))
    ZIO.fromPromiseScala(p)
  }

  def checkSession(implicit cb: Callback): Task[js.Dynamic] = {
    val p = Promise[js.Dynamic]()
    val scb = () => p.success(literal())
    val fcb = () => p.failure(js.JavaScriptException("wx.checkSession"))
    wx.checkSession(literal(success = scb, fail = fcb, complete = cb))
    ZIO.fromPromiseScala(p)
  }
}

object Main {
  def main(args: Array[String]): Unit = {
    import pages._
    module.exports.index =
      literal(
        "onLoad" -> index.onLoad _,
        "onShow" -> index.onShow _,
      )
  }
}
