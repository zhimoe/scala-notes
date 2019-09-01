// 规则1:{}表示code block,你可以在里面放几乎任何语句,block的返回值是由最后一句决定
// 规则2:block内容如果只有一句可以省略{},但是case clause除外:{case ...}
// 规则3: 单参数方法如果实参是code block,那么可以省略()
{
  import util.Try
  println {
    "hello"
  }
  5
}

val tupleList = List[(String, String)]()

//规则2
tupleList takeWhile ({
  case (t1, t2) => t1 == t2
})

// 规则2
List(1, 2, 3).reduceLeft(_ + _)

// 一种特殊情况,提示:隐式转换
val r = List(1, 2, 3).foldLeft(0) {
  _ + _
}
//val l = r{"hello"}

//不要调用这个方法
def loopf(x: Int): Int = loopf {
  x
}

//使用{}的特殊情况:for推导可以和()互换,一般建议是除了yield的其他情况都用()
for {tpl <- tupleList} yield tpl._2

//不建议
for {tpl <- tupleList} {
  println(tpl)
}
//推荐
for (tpl <- tupleList) {
  println(tpl)
}

//补充, 方法定义时如果没有返回值可以省略=,称为procedure,scala 2.13已经废弃,不要这么写
//don't
def p(in: String) {
  println(s"hello $in")
}



