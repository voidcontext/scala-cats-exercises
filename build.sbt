
val catsVersion = "1.0.1"

val defaultDependencies = Seq(
  "org.typelevel" %% "cats-core" % catsVersion,
  "org.typelevel" %% "cats-laws" % catsVersion,
  "org.scalatest" %% "scalatest" % "3.0.5" % "test",
  "org.scalacheck" %% "scalacheck" % "1.13.4" % "test"
)

val root = (project in file("exercises"))
  .settings(
    libraryDependencies ++= defaultDependencies
  )

