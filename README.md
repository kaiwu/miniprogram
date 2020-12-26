# Miniprogram meets ZIO

Since last [update on WeApp meets Scala.js](https://github.com/kaiwu/weui-scalajs), a great many have progressed:

1. Scala has released 2.13.4
2. Scala.js has released 1.3.1
3. Sbt has released 1.4.5
4. ZIO was born and it is 1.0.3 now
5. Miniprogram has also significant changes and it supports modules !

Phew !!! What is cooler than writing miniprograms with Scala.js? write them in ZIO !!!

```Scala
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

  def getUser(e: js.Dynamic): Unit = {
    val env = console.Console.live ++ Grant.live
    runtime
      .unsafeRunAsync(load(e.detail.errMsg.asInstanceOf[String])
      .provideLayer(env))(_ => println("DONE"))
  }
```

## How to use

### 1. install the customized sbt-less plugin

A customized `sbt-less` can be found in `project/lib`, it generates directly `.wxss` from the `.less` files, either copy it
to your ivy local or add the path in your `~/.sbt/repositories` such as

```
[repositories]
  local
  wechat: file:///{baseDirectory}/project/lib, [organization]/[module]/(scala_[scalaVersion]/)(sbt_[sbtVersion]/)[revision]/[type]s/[artifact](-[classifier]).[ext]
```

### 2. tasks

```
sbt > fullOptJS
sbt > less
sbt > assets
```
- fullOptJS compiles scala files to js files
- less compiles less files to wxss files
- assets copies the rest

### 3. cleanup

Now we have the miniprogram in `target` after cleanup, Scala.js generates straight ES6 and it fits miniprograms perfectly

```
shell $ ./cleanup.sh
```

## Caveats

1. For miniprograms ZIO is still huge ! luckily miniprogram supports modules now, so we can maintain a single compiled ZIO in `common` and proxy the calls from pages
2. Compile ZIO is relatively slow ... apparently my hardware has not progressed as much 
3. Next update ? probably after Scala 3 ...
   
## License

[MIT](http://opensource.org/licenses/MIT)
