package vdx.exercise01.printable

import vdx.exercise01.printable.PrintableInstances._
import vdx.exercise01.printable.PrintableSyntax._

object PrintableApp extends App {

  implicit val CatPrintable: Printable[Cat] = new Printable[Cat] {
    override def format(value: Cat): String = {
      val name = Printable.format(value.name)
      val age = Printable.format(value.age)
      val color= Printable.format(value.color)

      s"${name} is a ${age} year-old ${color} cat."
    }
  }


  val cat = Cat("Whiskers", 1, "black")

  cat.print
}
