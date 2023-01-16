$if(useScala3.truthy)$
ThisBuild / scalaVersion     :=  "3.2.1"
$else$
ThisBuild / scalaVersion     :=  "2.13.8"
$endif$

ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "com.example"
ThisBuild / organizationName := "example"

val dependencies = new {
  lazy val zio = "2.0.5"
  lazy val zioSpark = "0.11.5"
  $if(useScala3.truthy)$
  lazy val zioDirect = "1.0.0-RC1"
  $endif$
  lazy val spark = "3.3.1"
}

lazy val root = (project in file("."))
  .settings(
    name := "$name$",
    testFrameworks += new TestFramework("zio.test.sbt.ZTestFramework"),
    libraryDependencies ++= Seq(
      "dev.zio" %% "zio" % dependencies.zio,
      "dev.zio" %% "zio-test" % dependencies.zio % Test,
      "io.univalence" %% "zio-spark" % dependencies.zioSpark,
$if(useScala3.truthy)$
      "dev.zio" % "zio-direct_3" % dependencies.zioDirect,
      ("org.apache.spark" %% "spark-core" % dependencies.spark).cross(CrossVersion.for3Use2_13),
      ("org.apache.spark" %% "spark-sql" % dependencies.spark).cross(CrossVersion.for3Use2_13)
$else$
      "org.apache.spark" %% "spark-core" % dependencies.spark,
      "org.apache.spark" %% "spark-sql" % dependencies.spark
$endif$
    )
  )

// See https://www.scala-sbt.org/1.x/docs/Using-Sonatype.html for instructions on how to publish to Sonatype.
