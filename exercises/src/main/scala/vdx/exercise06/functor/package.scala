package vdx.exercise06

import cats.Functor
import cats.syntax.functor._
import vdx.{Branch, Leaf, Tree}

// Section 3.5.4
package object functor {
  implicit val treeFunctor = new Functor[Tree] {
    override def map[A, B](fa: Tree[A])(f: A => B): Tree[B] = fa match {
      case Leaf(value)         => Leaf(f(value))
      case Branch(left, right) => Branch(map(left)(f), map(right)(f))
    }
  }

  Tree.leaf(2).map(_ * 2)

  Tree.branch(Tree.leaf(1), Tree.leaf(2)).map(_ * 2)
}
