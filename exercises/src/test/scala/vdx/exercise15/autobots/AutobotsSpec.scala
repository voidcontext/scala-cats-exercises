package vdx.exercise15.autobots

import org.scalatest.{FlatSpec, Matchers}

class AutobotsSpec extends FlatSpec with Matchers {
  "tacticalReport" should "display the report when combined power level is sufficient" in {
    tacticalReport("Bumblebee", "Hot Rod") should be("Bumblebee and Hot Rod are ready to roll out!")
  }

  it should "display the report when combined power level is insufficient" in {
    tacticalReport("Jazz", "Bumblebee") should be("Jazz and Bumblebee need a recharge.")
  }

  it should "display the error message when there's any" in {
    tacticalReport("Jazz", "Ironhide") should be("Comms error: Ironhide unreachable")
  }
}
