package vdx.exercise06.functor

import org.typelevel.discipline.scalatest.Discipline
import cats.instances.AllInstances
import cats.laws.discipline.FunctorTests
import cats.syntax.AllSyntax
import org.scalatest.FunSuite
import vdx.TreeInstances

class FunctorSpec extends FunSuite with Discipline with AllSyntax with AllInstances with TreeInstances {

  checkAll("TreeFunctor", FunctorTests(treeFunctor).functor[Int, Int, Int])
}
