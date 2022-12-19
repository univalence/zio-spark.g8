import Dependencies._

$if(useScala3.truthy)$
ThisBuild / scalaVersion     :=  "3.2.1"
$else$
ThisBuild / scalaVersion     :=  "2.13.8"
$endif$

ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "com.example"
ThisBuild / organizationName := "example"

lazy val root = (project in file("."))
  .settings(
    name := "$name$",
    testFrameworks += new TestFramework("zio.test.sbt.ZTestFramework"),
    libraryDependencies ++= Seq(
      "dev.zio" %% "zio" % Dependencies.zio,
      "dev.zio" %% "zio-test" % Dependencies.zio % Test,
      "io.univalence" %% "zio-spark" % Dependencies.zioSpark,
$if(useScala3.truthy)$
      "dev.zio" % "zio-direct_3" % Dependencies.zioDirect,
      //as for today : 2022-12-19, the encoders don't work for spark 3.3.x
      ("org.apache.spark" %% "spark-core" % Dependencies.spark).cross(CrossVersion.for3Use2_13),
      ("org.apache.spark" %% "spark-sql" % Dependencies.spark).cross(CrossVersion.for3Use2_13)
$else$
      "org.apache.spark" %% "spark-core" % Dependencies.spark,
      "org.apache.spark" %% "spark-sql" % Dependencies.spark
$endif$
    )
  )

// See https://www.scala-sbt.org/1.x/docs/Using-Sonatype.html for instructions on how to publish to Sonatype.
