package vdx.exercise04

import cats.{Monoid, Semigroup}

// Section 2.3 and 2.4
package object monoid {

  object BooleanMonoidOrInstance {
    implicit val booleanMonoid: Monoid[Boolean] = new Monoid[Boolean] {
      override def empty: Boolean = false

      override def combine(x: Boolean, y: Boolean): Boolean = x || y
    }
  }

  object BooleanMonoidAndInstance {
    implicit val booleanMonoid: Monoid[Boolean] = new Monoid[Boolean] {
      override def empty: Boolean = true

      override def combine(x: Boolean, y: Boolean): Boolean = x && y
    }
  }

  object BooleanMonoidXorInstance {
    implicit val booleanMonoid: Monoid[Boolean] = new Monoid[Boolean] {
      override def empty: Boolean = false

      override def combine(x: Boolean, y: Boolean): Boolean = x ^ y
    }
  }

  object BooleanMonoidXnorInstance {
    implicit val booleanMonoid: Monoid[Boolean] = new Monoid[Boolean] {
      override def empty: Boolean = true

      override def combine(x: Boolean, y: Boolean): Boolean = x == y
    }
  }

  object SetMonoidUnionInstance {
    implicit def setMonoid[A]: Monoid[Set[A]] = new Monoid[Set[A]] {
      override def empty: Set[A] = Set.empty

      override def combine(x: Set[A], y: Set[A]): Set[A] = x.union(y)
    }
  }

  object SetSemigroupIntersectInstance {
    implicit def setSemigroup[A]: Semigroup[Set[A]] = new Semigroup[Set[A]] {
      override def combine(
        x: Set[A],
        y: Set[A]
      ): Set[A] = x.intersect(y)
    }
  }

  object SetSemigroupSymmetricDifferenceInstance {
    implicit def setSemigroup[A]: Semigroup[Set[A]] = new Semigroup[Set[A]] {
      override def combine(
        x: Set[A],
        y: Set[A]
      ): Set[A] = x.diff(y).intersect(y.diff(x))
    }
  }

  object Monoid {
    def apply[A](implicit monoid: Monoid[A]) = monoid
  }
}
