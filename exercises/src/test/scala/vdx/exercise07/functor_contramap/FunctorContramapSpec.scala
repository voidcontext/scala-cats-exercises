package vdx.exercise07.functor_contramap

import org.scalatest.{FlatSpec, Matchers}

class FunctorContramapSpec extends FlatSpec with Matchers {
  "format[A](value: A)" should "keep strings as they are" in {
    format("Hello World") should be("Hello World")
  }

  it should "tranform boolean true to 'yes''" in {
    format(true) should be("yes")
  }

  "Printable[Box]" should "unwrap the inner string value and format it" in {
    format(Box("Hello World")) should be("Hello World")
  }

  it should "unwrap the inner boolean value and format it" in {
    format(Box(true)) should be("yes")
  }
}
