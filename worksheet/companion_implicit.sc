
class Person(name: String, age: Int) {
  private var skill: String = "no skill"

  def introduce() = println(s"my name is $name, I am $age years old")
}

// companion object name should be identical to the class name.
object Person {
  def apply(name: String, age: Int): Person = {
    new Person(name, age)
  }

  //apply method override
  def apply(name: String, age: Int, skill: String): Person = {
    val p = new Person(name, age)
    p.skill = skill
    p
  }
}

val dahu = Person("dahu", 30)
dahu.introduce
//more about apply method
val list = List("1", "2", "3")

//## implicit
//1. implicit parameter
class Prefixer(val prefix: String)

def addPrefix(s: String)(implicit p: Prefixer) = p.prefix + s

// then probably in your application
implicit val myImplicitPrefixer = new Prefixer("***")
addPrefix("abc") // returns "***abc"

//2. imiplicit conversion(implicit conversion method)
//add a mkStr method to scala String object
class StrOps {
  var content: String = "hello,StrOps"

  def mkStr(): String = {
    return "Ops!  " + content
  }
}

implicit def str2StrOps(s: String) = {
  val ops = new StrOps()
  ops.content = s
  ops
}

val s = "who changed my string"
s.mkStr()
//WrappedString,StringOps,RichInt,RichDouble
"abc".map(_.toInt)
// implicit scope
// implicit conversion method should only one in context scope

import scala.math._

def chg[T](t: T)(implicit integral: Integral[T]) {
  println(integral)
}
chg(1) //scala.math.Numeric$IntIsIntegral$@660c4b4a
chg(0L) //scala.math.Numeric$LongIsIntegral$@5c05688b

//First look in current scope
//    Implicits defined in current scope
//    Explicit imports
//    wildcard imports
//
//Now look at associated types in
//    Companion objects of a type
//    Implicit scope of an argument’s type (2.9.1)
//    Implicit scope of type arguments (2.8.0)
//    Outer objects for nested types

//import scala.collection.JavaConversions.mapAsScalaMap

import scala.collection.JavaConverters._

def env = System.getenv() // Java map
// implicit conversion from Java Map to Scala Map
val term = env.asScala("JAVA_HOME")


//implicitly function in Predef
def implicitly[T](implicit e: T): T = e
implicit val a = "test"
val b = implicitly[String]
  //val c = implicitly[Int]
  .getClass

for {
  x <- List(1, 2, 3)
  y <- Some('x')
} yield (x, y)

"abc".indexOf("b")
"cabc".indexOf("c")

// Predef

import scala.Predef


// pattern matching anonymous function
val wordFrequencies = ("habitual", 6) :: ("and", 56) :: ("consuetudinary", 2) ::
  ("additionally", 27) :: ("homely", 5) :: ("society", 13) :: Nil
def wordsWithoutOutliers(wordFrequencies: Seq[(String, Int)]): Seq[String] =
  wordFrequencies.filter(wf => wf._2 > 3 && wf._2 < 25).map(_._1)
wordsWithoutOutliers(wordFrequencies) // List("habitual", "homely", "society")

// a better way
def wordsWithoutOutliers2(wordFrequencies: Seq[(String, Int)]): Seq[String] =
  wordFrequencies.filter { case (_, f) => f > 3 && f < 25 } map { case (w, _) => w }

wordsWithoutOutliers2(wordFrequencies)

//use partialFunction
def pf: PartialFunction[(String, Int), String] = {
  case (w, f) if f > 3 && f < 25 => w
}

wordFrequencies.collect(pf)

import collection.JavaConverters._


class A(val n: Int) {
  override def toString(): String = {
    return n.toString
  }
}

object A {
  implicit val ord = new Ordering[A] {
    def compare(x: A, y: A) = implicitly[Ordering[Int]].compare(x.n, y.n)
  }
}

val sl = List(new A(5), new A(2)).sorted


