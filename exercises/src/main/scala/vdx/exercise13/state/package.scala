package vdx.exercise13

import cats.data.State
import cats.syntax.applicative._

package object state {
  type CalcState[A] = State[List[Int], A]

  def evalOne(sym: String): CalcState[Int] = State[List[Int], Int] { oldStack =>
    def calc(stack: List[Int], f: (Int, Int) => Int) = stack match {
      case a :: b :: tail =>
        val result = f(b, a)
        (result :: tail, result)
    }

    sym match {
      case "+" => calc(oldStack, _ + _)
      case "-" => calc(oldStack, _ - _)
      case "*" => calc(oldStack, _ * _)
      case _ =>
        (sym.toInt :: oldStack, sym.toInt)
    }
  }

  def evalAll(input: List[String]): CalcState[Int] = input.foldLeft(0.pure[CalcState])(
    (state, sym) => state.flatMap(_ => evalOne(sym))
  )
}
