//_ 表示通配符，占位符，忽略的意思
class Reference[T] {
  private var contents: T = _ //初始化，T是Int，则contents是0，T是boolean，则是false；Unit则是()
}

List(1, 2, 3) foreach (print _) // match anything

//在匿名函数中作为参数占位符：
List(1, 2, 3) map (_ + 2) // _ + 2是一个匿名函数

//模式匹配中的最后一行作为通配符
val expr = List()
expr match {
  case List(1, _, _) => " a list with three element and the first element is 1"
  case List(_*) => " a list with zero or more elements "
  case Seq(xs@_ *) => "等价将expr重命名为xs"
  case _ => "通配符,Discarded parameter"
}
val List(x, xs @ _*) = List(1, 2, 3)
//x: Int = 1
//xs: Seq[Int] = List(2, 3)

//import中作为通配符和隐藏符
//第一个下划线表示discard ArrayList，第二个表示通配符，导入所有
import java.util.{ArrayList => _, _}

//将方法变为value
def method(i: Int): Int = {
  i + 10
}
val mVal = method _ // Eta expansion of method into method value

//tuple 的访问
val tpl = ("a", "b")
tpl._2 //返回tpl第二个元素，注意，tuple是从1开始的

//特殊变量名中操作符之前需要使用_,否则会报错,可以试着删除下面的_
def abc_<>!() // An underscore must separate alphanumerics from symbols on identifiers

//泛型方法的类型占位符 Existential type
trait IterableOps[+A, +CC[_], +C] {

}
def f[M[_]]      // Higher kinded type parameter

def ef(m: M[_])

