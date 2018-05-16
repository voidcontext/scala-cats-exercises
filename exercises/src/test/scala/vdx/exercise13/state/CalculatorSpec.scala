package vdx.exercise13.state

import org.scalatest.{FlatSpec, Matchers}

class CalculatorSpec extends FlatSpec with Matchers {
  "evalOne(sym)" should "be able to add 2 numbers" in {
    val result = for {
      _ <- evalOne("1")
      _ <- evalOne("2")
      ans <- evalOne("+")
    } yield ans

    result.runA(List.empty).value should be(3)
  }

  "evalAll(...)" should "evaluate the whole computation" in {
    val result = evalAll(List("2", "3", "+", "4", "-")).runA(List.empty).value

    result should be(1)
  }
}
