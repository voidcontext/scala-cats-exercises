package vdx.exercise11

import cats.data.Writer
import cats.instances.vector._
import cats.syntax.applicative._
import cats.syntax.writer._

package object writer {
  def slowly[A](body: => A) =
    try body finally Thread.sleep(100)

  type Logged[A] = Writer[Vector[String], A]

  def factorialNaive(n: Int): Logged[Int] = {
    def logText(v: Int) = s"fact $n ${v.toString}"
    val ans = slowly(if(n == 0) Writer(Vector(logText(1)), 1) else {
      val w = factorial(n - 1).map(n * _)
      w.tell(Vector(logText(w.value)))
    })
    //    println(s"fact $n $ans")
    ans
  }

  def factorial(n: Int):  Logged[Int] = {
    for {
      ans <- if (n == 0) 1.pure[Logged]
             else slowly(factorial(n - 1).map(n * _))
      _   <- Vector(s"fact $n $ans").tell
    } yield ans
  }

  def factorialExpanded(n: Int): Logged[Int] = {
    (if (n == 0) 1.pure[Logged] else slowly(factorial(n - 1).map(n * _))).flatMap(
      ans =>
        Vector(s"fact $n $ans").tell.map(
          _ => ans
        )
    )
  }

}
