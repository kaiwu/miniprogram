package miniprogram

import scala.scalajs.js
import scala.scalajs.js.annotation._

@js.native
@JSGlobal
object App extends js.Object {
  def apply(o: js.Object): Unit = js.native
}

@js.native
@JSGlobal
class Page extends js.Object {
  def setData(o: js.Dynamic, f: js.Function): Unit = js.native
}

@js.native
@JSGlobal
object Page extends js.Object {
  def apply(o: js.Object): Unit = js.native
}

@js.native
@JSGlobalScope
object WXGlobal extends js.Object {
  def getApp(): js.Dynamic = js.native
  def getCurrentPages(): js.Array[Page] = js.native
  def setTimeout(callback: js.Function, timeout: Int): Int = js.native
  def clearTimeout(timer: Int): Unit = js.native
  def require(file: String): js.Dynamic = js.native
}

@js.native
@JSGlobal
class RequestTask extends js.Object {
  def abort (): Unit = js.native
}

@js.native
@JSGlobal("module")
object module extends js.Object {
  val exports: js.Dynamic = js.native
}

@js.native
@JSGlobal("wx")
object wx extends js.Object {
  def request (o: js.Object): RequestTask = js.native

  def setStorage (o: js.Object): Unit = js.native
  def getStorage (o: js.Object): Unit = js.native
  def removeStorage (o: js.Object): Unit = js.native
  def clearStorage(): Unit = js.native

  def saveFile (o: js.Object): Unit = js.native
  def getFileInfo (o: js.Object): Unit = js.native
  def getSavedFileList (o: js.Object): Unit = js.native
  def getSavedFileInfo (o: js.Object): Unit = js.native
  def removeSavedFile (o: js.Object): Unit = js.native
  def openDocument (o: js.Object): Unit = js.native

  def getSystemInfo (o: js.Object): Unit = js.native
  def canIUse(feature: String): Boolean = js.native

  def login (o: js.Object): Unit = js.native
  def checkSession (o: js.Object): Unit = js.native
  def getSetting (o: js.Object): Unit = js.native
  def authorize (o: js.Object): Unit = js.native

  def getLocation (o: js.Object): Unit = js.native
  def chooseLocation (o: js.Object): Unit = js.native
  def openLocation (o: js.Object): Unit = js.native

  def setNavigationBarTitle (o: js.Object): Unit = js.native
  def setNavigationBarColor (o: js.Object): Unit = js.native

  def navigateTo(o: js.Object): Unit = js.native
  def navigateToMiniProgram (o: js.Object): Unit = js.native
}

