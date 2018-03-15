package vdx.exercise04.monoid

import org.typelevel.discipline.scalatest.Discipline
import cats.instances.AllInstances
import cats.kernel.laws.discipline.MonoidTests
import cats.syntax.AllSyntax
import org.scalatest.FunSuite

class MonoidSpec extends FunSuite with Discipline with AllInstances with AllSyntax {
  checkAll("MonoidAnd", MonoidTests(BooleanMonoidAndInstance.booleanMonoid).monoid)
  checkAll("MonoidOr", MonoidTests(BooleanMonoidOrInstance.booleanMonoid).monoid)
  checkAll("MonoidXor", MonoidTests(BooleanMonoidXorInstance.booleanMonoid).monoid)
  checkAll("MonoidXnor", MonoidTests(BooleanMonoidXnorInstance.booleanMonoid).monoid)
}
