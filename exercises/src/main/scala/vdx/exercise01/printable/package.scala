package vdx.exercise01

package object printable {
  final case class Cat(name: String, age: Int, color: String)

  trait Printable[A] {
    def format(value: A): String
  }

  object PrintableInstances {
    implicit val StringPrintable: Printable[String] = new Printable[String] {
      override def format(value: String): String = value
    }

    implicit val IntPrintable: Printable[Int] = new Printable[Int] {
      override def format(value: Int): String = value.toString
    }
  }

  object PrintableSyntax {
    implicit class PrintableOps[A](value: A) {
      def format(implicit p: Printable[A]): String = p.format(value)

      def print(implicit p: Printable[A]): Unit = println(p.format(value))
    }
  }

  object Printable {
    def format[A](value: A)(implicit printable: Printable[A]): String = printable.format(value)

    def print[A](value: A)(implicit printable: Printable[A]): Unit = println(printable.format(value))
  }
}
