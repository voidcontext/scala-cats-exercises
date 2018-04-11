package vdx.exercise11.writer

import cats.data.Writer
import org.scalatest.{FlatSpec, Matchers}

import scala.concurrent.{Await, Future}
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global

class FactorialSpec extends FlatSpec with Matchers {
  def suite(name: String, factorial: Int => Logged[Int]) = {
    name should "collect logs in a writer" in {
      val result = Await.result(Future.sequence(Vector(
        Future(factorial(3)),
        Future(factorial(3))
      )), 5.seconds)

      result.head shouldBe a[Writer[_, _]]
    }


    it should "calculate the correct value" in {
      val result = Await.result(Future.sequence(Vector(
        Future(factorial(5)),
        Future(factorial(5))
      )), 5.seconds)

      result.head.value should be(120)
    }

    it should "provide the correct logs" in {
      val result = Await.result(Future.sequence(Vector(
        Future(factorial(3)),
        Future(factorial(3))
      )), 5.seconds)

      result.head.written should equal(Vector("fact 0 1", "fact 1 1", "fact 2 2", "fact 3 6"))
    }
  }

  suite("factorial(n)", factorial)
  suite("factorialExpanded(n)", factorialExpanded)
  suite("factorialNaive(n)", factorialNaive)
}
