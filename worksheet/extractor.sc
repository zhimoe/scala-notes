
trait User

class FreeUser(
                val name: String,
                val score: Int,
                val upgradeProbability: Double)
  extends User

class PremiumUser(
                   val name: String,
                   val score: Int)
  extends User

object FreeUser {
  def unapply(user: FreeUser): Option[(String, Int, Double)] =
    Some((user.name, user.score, user.upgradeProbability))
}

object PremiumUser {
  def unapply(user: PremiumUser): Option[(String, Int)] =
    Some((user.name, user.score))
}

val freeUsr = new FreeUser("john", 70, 0.5)
freeUsr match {
  case FreeUser(name, _, p) => if (p > 0.75) println(s"what can I do for you,$name")
  else println(s"hello,$name")
  case _ => println("who are you")
}

object premiumCandidate {
  def unapply(user: FreeUser): Boolean = user.upgradeProbability > 0.4
}

freeUsr match {
  case freeUser@premiumCandidate() => println(s"恭喜成为黄金会员候选人")
  case _ => println("欢迎回来")
}

//####################infix pattern match
val xs = 58 #:: 43 #:: 93 #:: Stream.empty


