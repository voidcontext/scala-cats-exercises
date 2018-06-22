package vdx.exercise14.custom_monads

import cats.Eq
import cats.instances.AllInstances
import cats.laws.discipline.MonadTests
import cats.syntax.AllSyntax
import org.scalacheck.{Arbitrary, Gen}
import org.scalatest.{FunSuite, Matchers}
import org.typelevel.discipline.scalatest.Discipline
import vdx.{Branch, Leaf, Tree, TreeInstances}

class TreeMonadSpec extends FunSuite with Discipline with AllSyntax with AllInstances with Matchers with TreeInstances {

  checkAll("TreeMonads", MonadTests(treeMonad).monad[Int, Int, Int])

  test("Leaf flatMap") {
    Tree.leaf(2).flatMap(v => Tree.leaf(v * 2)) should equal(Leaf(4))
  }

  test("Branch flatMap") {
    Tree.branch(Tree.leaf(1), Tree.leaf(2)).flatMap(v => Tree.leaf(v * 2)) should equal(
      Tree.branch(Tree.leaf(2), Tree.leaf(4))
    )
  }

  test(" Deep flatMap") {
    Tree
      .branch(Tree.leaf(100), Tree.leaf(200))
      .flatMap(x => Tree.branch(Tree.leaf(x - 1), Tree.leaf(x + 1))) should equal(
      Branch(Branch(Leaf(99), Leaf(101)), Branch(Leaf(199), Leaf(201)))
    )
  }

  test("for comprehension") {
    (for {
      a <- Tree.branch(Tree.leaf(100), Tree.leaf(200))
      b <- Tree.branch(Tree.leaf(a - 10), Tree.leaf(a + 10))
      c <- Tree.branch(Tree.leaf(b - 1), Tree.leaf(b + 1))
    } yield c) should equal(
      Branch(
        Branch(Branch(Leaf(89), Leaf(91)), Branch(Leaf(109), Leaf(111))),
        Branch(Branch(Leaf(189), Leaf(191)), Branch(Leaf(209), Leaf(211)))
      )
    )
  }
}
