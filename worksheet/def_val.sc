//主要两个区别：
//	1. def是在调用时evaluate，每次都是一个新的函数，val是表达式，定义时就已经evaluate。
//	2. method只能用def声明,function可以用val或者def

def even: Int => Boolean = _ % 2 == 0
even eq even //false

val even2: Int => Boolean = _ % 2 == 0
even2 eq even2 //true


def m: Int => Int = ??? //??? 表示未实现方法,注意不能和 = 贴近
//val f:Int=>Int = ???

//lazy val: evaluate when called first time
//lazy val lf:Int=>Int = ???


//#######################function vs method
// function is value,is an object of FunctionN,which has apply method
trait Function2[-T1, -T2, +R] extends AnyRef

// since function is value, you can call method on it,but can not call mehtod on method, cuz it belongs to value
even2.toString()
def md(i: Int): Int = {
  i + 10
}

//md.toString

class Clz {
  def m1(x: Int) = x + 3

  val f1 = (x: Int) => x + 3
}

//Clz.class and Clz$$anonfun$1.class


def m1: Int => Int = _ + 10
val f1 = m1 _ //下划线z表示参数列表 eta-expansion
val f2: (Int) => Int = m1 //m1 的入参和返回值要和f2的一样


