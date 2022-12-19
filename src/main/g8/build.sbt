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
$if(useScala3.truthy)$
    libraryDependencies ++= Seq(
      "dev.zio" %% "zio" % "2.0.5",
      "dev.zio" % "zio-direct_3" % "1.0.0-RC1",
      "io.univalence" %% "zio-spark" % "0.10.0",
      //as for today : 2022-12-19, the encoders don't work for spark 3.3.x
      ("org.apache.spark" %% "spark-sql" % "3.2.0" % Provided).cross(CrossVersion.for3Use2_13),
      "org.apache.hadoop" % "hadoop-client" % "3.2.0" % Provided,
      "dev.zio" %% "zio-test" % "2.0.5" % Test
    )
$else$
   libraryDependencies ++= Seq(
     "dev.zio" %% "zio" % "2.0.5",
     "io.univalence" %% "zio-spark" % "0.10.0",
     "org.apache.spark" %% "spark-sql" % "3.3.1" % Provided,
     "org.apache.hadoop" % "hadoop-client" % "3.3.1" % Provided,
     "dev.zio" %% "zio-test" % "2.0.5" % Test
   )
$endif$
  )

// See https://www.scala-sbt.org/1.x/docs/Using-Sonatype.html for instructions on how to publish to Sonatype.
