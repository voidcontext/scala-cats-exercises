package vdx

import cats.Eq
import org.scalacheck.{Arbitrary, Gen}

trait TreeInstances {
  def mul2: Int => Int = _ * 2

  implicit val arbTreeInt: Arbitrary[Tree[Int]] = Arbitrary(
    Gen.oneOf(Leaf(1), Leaf(0), Branch(Leaf(1), Branch(Leaf(2), Leaf(3))))
  )
  implicit val arbTreeIntToInt: Arbitrary[Tree[Int => Int]] = Arbitrary(
    Gen.oneOf(Leaf(mul2), Leaf(mul2), Branch(Leaf(mul2), Branch(Leaf(mul2), Leaf(mul2))))
  )

  implicit val eqTreeInt: Eq[Tree[Int]] = new Eq[Tree[Int]] {
    override def eqv(
      x: Tree[Int],
      y: Tree[Int]
    ): Boolean = (x, y) match {
      case (Leaf(a), Leaf(b))               => a == b
      case (Branch(xl, xr), Branch(yl, yr)) => eqv(xl, yl) && eqv(xr, yr)
      case _                                => false
    }
  }

  implicit val eqTree3TupleInt: Eq[Tree[(Int, Int, Int)]] = new Eq[Tree[(Int, Int, Int)]] {
    override def eqv(
      x: Tree[(Int, Int, Int)],
      y: Tree[(Int, Int, Int)]
    ): Boolean = (x, y) match {
      case (Leaf(a), Leaf(b))               => a._1 == b._1 && a._2 == b._2 && a._3 == b._3
      case (Branch(xl, xr), Branch(yl, yr)) => eqv(xl, yl) && eqv(xr, yr)
      case _                                => false
    }
  }

}
