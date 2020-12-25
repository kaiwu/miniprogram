package pages

import zio._
import ZIO._
import scala.scalajs.js
import js.Dynamic.literal
import miniprogram.Wechat._

object index {
  type Grant = Has[Grant.Service]
  object Grant {
    trait Service {
      def approve(condition: String): UIO[Boolean]
    }

    val live: Layer[Nothing, Grant] = ZLayer.succeed(
      new Service {
        def approve(condition: String) = condition match {
          case "getUserInfo:ok" => IO.succeed(true)
          case _                => IO.succeed(false)
        }
      }
    )

    def approve(condition: String): URIO[Grant, Boolean] = ZIO.accessM(_.get.approve(condition))
  }


  val runtime = Runtime.default
  def load(condition: String): ZIO[console.Console with Grant, Throwable, Unit] = for {
    -        <- console.putStrLn(s"condition is ${condition}")
    approve  <- Grant.approve(condition)
    -        <- console.putStrLn(s"approve is ${approve}")
    if approve
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
    val env = console.Console.live ++ Grant.live
    runtime.unsafeRunAsync(load(e.detail.errMsg.asInstanceOf[String]).provideLayer(env))(_ => println("DONE"))
  }
}

