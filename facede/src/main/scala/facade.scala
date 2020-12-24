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
}

