package vdx.exercise06

import cats.Functor
import cats.syntax.functor._

// Section 3.5.4
package object functor {
  sealed trait Tree[+A]
  final case class Branch[A](left: Tree[A], right: Tree[A]) extends Tree[A]

  final case class Leaf[A](value: A) extends Tree[A]

  implicit val treeFunctor = new Functor[Tree] {
    override def map[A, B](fa: Tree[A])(f: A => B): Tree[B] = fa match {
      case Leaf(value)         => Leaf(f(value))
      case Branch(left, right) => Branch(map(left)(f), map(right)(f))
    }
  }

  object Tree {
    def branch[A](left: Tree[A], right: Tree[A]): Tree[A] = Branch(left, right)

    def leaf[A](value: A): Tree[A] = Leaf[A](value)
  }

  Tree.leaf(2).map(_ * 2)

  Tree.branch(Tree.leaf(1), Tree.leaf(2)).map(_ * 2)
}
