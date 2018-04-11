package vdx.exercise10

import cats.Eval

package object eval {
  def foldRight[A, B](as: List[A], acc: B)(fn: (A, B) => B): B = {
    def transform(a: A, b: Eval[B]) = {
      b.map(b => fn(a,b))
    }
    safeFoldRight(as, acc)(transform).value
  }

  def safeFoldRight[A, B](as: List[A], acc: B)(fn: (A, Eval[B]) => Eval[B]): Eval[B] = as match {
    case head :: tail =>
      Eval.defer(fn(head, safeFoldRight(tail, acc)(fn)))
    case Nil =>
      Eval.now(acc)
  }
}
