package vdx.exercise04

import cats.Monoid

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

  object Monoid {
    def apply[A](implicit monoid: Monoid[A]) = monoid
  }
}
