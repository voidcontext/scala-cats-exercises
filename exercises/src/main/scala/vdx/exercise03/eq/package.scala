package vdx.exercise03

import cats.Eq
import cats.instances.int._
import cats.instances.string._
import cats.syntax.eq._
import vdx.exercise01.printable.Cat

// Section 1.5.5
package object eq {
  object EqInstances {
    implicit val catEq: Eq[Cat] = (a: Cat, b: Cat) => a.name === b.name && a.age === b.age && a.color === b.color
  }
}
