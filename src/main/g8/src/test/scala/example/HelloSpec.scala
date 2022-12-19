package example

import zio.test._


object HelloWorldSpec extends ZIOSpecDefault {
  def spec =
    suite("HelloWorldSpec")(
      test("sayHello") {
        val h = "hello"
        assertTrue(h == "hello")
      }
    )
}