package vdx.exercise15

import cats.data.EitherT
import cats.instances.future._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{Await, Future}
import scala.concurrent.duration._

package object autobots {
  type Response[A] = EitherT[Future, String, A]

  val powerLevels = Map(
    "Jazz" -> 6,
    "Bumblebee" -> 8,
    "Hot Rod" -> 10
  )

  def getPowerLevel(autobot: String): Response[Int] = {
    powerLevels
      .get(autobot)
      .map[Response[Int]](plevel => EitherT.right(Future(plevel)))
      .getOrElse(EitherT.left(Future(s"$autobot unreachable")))
  }

  def canSpecialMove(ally1: String, ally2: String): Response[Boolean] = {
    for {
      power1 <- getPowerLevel(ally1)
      power2 <- getPowerLevel(ally2)
    } yield power1 + power2 > 15
  }

  def tacticalReport(ally1: String, ally2: String): String = {
    val reportResult = canSpecialMove(ally1, ally2).map { canSpec =>
      if (canSpec) s"$ally1 and $ally2 are ready to roll out!"
      else s"$ally1 and $ally2 need a recharge."
    }

    Await.result(reportResult.value, 1.seconds) match {
      case Left(err) => s"Comms error: $err"
      case Right(r)  => r
    }
  }
}
