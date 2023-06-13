# Miniprogram meets Cats Effect

As promised, this is an update from [update on WeApp meets Scala.js](https://github.com/kaiwu/weui-scalajs) 
leveraging both Scala 3 and [Cats Effect](https://typelevel.org/cats-effect/)

```Scala
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
## License

[MIT](http://opensource.org/licenses/MIT)
