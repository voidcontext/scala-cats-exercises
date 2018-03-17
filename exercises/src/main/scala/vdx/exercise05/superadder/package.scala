package vdx.exercise05

import cats.kernel.Monoid
import cats.syntax.monoid._
import cats.instances.double._

// Section 2.5.4
package object superadder {
  object SuperAdder1 {
    def add(items: List[Int]): Int = items.sum
  }

  object SuperAdder2 {
    def add[A: Monoid](items: List[A])(implicit monoid: Monoid[A]) = items.foldLeft(monoid.empty)(monoid.combine)
  }

  final case class Order(totalCost: Double, quantity: Double)

  implicit val orderMonoid: Monoid[Order] = new Monoid[Order] {
    override def empty: Order = Order(totalCost = 0, quantity = 0)

    override def combine(
      x: Order,
      y: Order
    ): Order = Order(
      x.totalCost.combine(y.totalCost),
      x.quantity.combine(y.quantity)
    )
  }
}
