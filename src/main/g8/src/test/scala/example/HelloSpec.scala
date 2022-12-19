package example

import zio.test._
import zio._


object HelloWorldSpec extends ZIOSpecDefault {
  def spec =
    suite("HelloWorldSpec")(
      test("sayHello") {
        val h = "hello"
        assertTrue(h == "hello")
      },

      test("smokeRun") {
        SimpleApp.provide(ZIOAppArgs(Chunk.empty))
      }
    )
}