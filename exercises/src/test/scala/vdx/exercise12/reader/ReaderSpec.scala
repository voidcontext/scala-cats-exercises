package vdx.exercise12.reader

import org.scalatest.{FlatSpec, Matchers}

class ReaderSpec extends FlatSpec with Matchers {
  val users = Map(
    1 -> "dade",
    2 -> "kate",
    3 -> "margo"
  )

  val passwords = Map(
    "dade"  -> "zerocool",
    "kate"  -> "acidburn",
    "margo" -> "secret"
  )

  val db = Db(users, passwords)

  "checkLogin(userId, password)" should "return false when user is not found" in {
    checkLogin(4, "foobar").run(db) should be(false)
  }

  it should "return false when the password doesn't match" in {
    checkLogin(1, "foobar").run(db) should be(false)
  }

  it should "return true when the username and password are matching" in {
    checkLogin(1, "zerocool").run(db) should be(true)
  }
}
