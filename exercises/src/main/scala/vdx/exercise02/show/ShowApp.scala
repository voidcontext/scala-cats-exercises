package vdx.exercise02.show

import cats.Show
import cats.syntax.show._
import cats.instances.int._
import cats.instances.string._
import vdx.exercise01.printable.Cat

object ShowApp extends App {

  implicit val CatPrintable: Show[Cat] = new Show[Cat] {
    def show(value: Cat): String = {
      val name = value.name.show
      val age = value.age.show
      val color= value.color.show

      s"${name} is a ${age} year-old ${color} cat."
    }
  }

  val cat = Cat("Whiskers", 1, "black")

  println(cat.show)
}
