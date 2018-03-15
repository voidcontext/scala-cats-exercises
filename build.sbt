
val catsVersion = "1.0.1"

val defaultDependencies = Seq(
  "org.typelevel" %% "cats-core" % catsVersion
)

val root = (project in file("exercises"))
  .settings(
    libraryDependencies ++= defaultDependencies
  )

