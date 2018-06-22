package vdx.exercise14

import cats.Monad
import vdx.{Branch, Leaf, Tree}

package object custom_monads {
  implicit val treeMonad = new Monad[Tree] {
    override def pure[A](x: A): Tree[A] = Tree.leaf(x)

    override def flatMap[A, B](fa: Tree[A])(f: A => Tree[B]): Tree[B] = fa match {
      case Branch(l, r) => Tree.branch(flatMap(l)(f), flatMap(r)(f))
      case Leaf(value)  => f(value)
    }

    override def tailRecM[A, B](a: A)(f: A => Tree[Either[A, B]]): Tree[B] = {
      def unwrap(a: Either[A, B]) = a match {
        case Left(v)  => tailRecM(v)(f)
        case Right(v) => Tree.leaf(v)
      }

      f(a) match {
        case Branch(l, r)   => Tree.branch[B](flatMap(l)(unwrap), flatMap(r)(unwrap))
        case Leaf(Left(v))  => tailRecM(v)(f)
        case Leaf(Right(v)) => Tree.leaf(v)
      }
    }
  }
}
