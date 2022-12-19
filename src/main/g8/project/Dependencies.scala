import sbt._

object Dependencies {
  lazy val zio = "2.0.5"
  lazy val zioSpark = "0.10.0"
  $if(useScala3.truthy)$
  lazy val spark = "3.2.0"
  lazy val zioDirect = "1.0.0-RC1"
  $else$
  lazy val spark = "3.3.1"
  $endif$
}
