package vdx.exercise12

import cats.Applicative
import cats.data.Reader

package object reader {
  case class Db(
    usernames: Map[Int, String],
    passwords: Map[String, String]
  )

  type DbReader[A] = Reader[Db, A]

  def findUsername(userId: Int): DbReader[Option[String]] =
    Reader(
      _.usernames.get(userId)
    )

  def checkPassword(username: String, password: String): DbReader[Boolean] =
    Reader(
      _.passwords.get(username).contains(password)
    )

  def checkLogin(userId: Int, password: String): DbReader[Boolean] =
    findUsername(userId).flatMap(
      _.map(username => checkPassword(username, password)).getOrElse(Reader(_ => false))
    )
}
