package vdx.exercise10.eval

import org.scalatest.{FlatSpec, Matchers}

class EvalSpec extends FlatSpec with Matchers {
  "foldRight" should "stacksafe" in {
    println(foldRight[Int, Int]((1 to 10000).toList, 0)(_ + _))

    true should be(true)
  }
}
