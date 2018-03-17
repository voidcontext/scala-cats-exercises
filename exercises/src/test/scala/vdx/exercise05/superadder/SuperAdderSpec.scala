package vdx.exercise05.superadder

import cats.instances.option._
import cats.instances.int._
import org.scalatest.{FlatSpec, Matchers}

class SuperAdderSpec extends FlatSpec with Matchers {
  "SuperAdder1" should "calculate the sum of a list of ints" in {
    SuperAdder1.add(List(1, 2, 3)) should be(6)
  }

  "SuperAdder2" should "calculate the sum of a list of Option[Int]" in {
    SuperAdder2.add(List(None, Some(1), Some(3))) should be(Some(4))
  }

  it should "calculate the sum of a list of orders" in {
    SuperAdder2.add(List(Order(2, 5), Order(1, 4))) should be(Order(3, 9))
  }
}
