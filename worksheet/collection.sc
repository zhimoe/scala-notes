//map reduce fold for in collection
//map 是将一个函数apply到集合所有的元素中
//reduce是一个二元函数,遍历整个集合
List(1, 3, 5).reduceLeft(_ + _)
// == ((1+3)+5)
//reduceRight start from end of the collection
//also you can given initial argument
List(1, 3, 5).foldLeft("")(_ + _)
// == 135
//foldLeft 等价于 \: 操作符
(0 /: List(1, 3, 5)) (_ - _)

//folding 常用于替代for-loop
val wf1 = scala.collection.mutable.Map[Char, Int]()
for (c <- "Mississippi") wf1(c) = wf1.getOrElse(c, 0) + 1
// Now freq is Map('i' -> 4, 'M' -> 1, 's' -> 4, 'p' -> 2)

//注意使用了不可变map
val wf = (Map[Char, Int]() /: "Mississippi") {
  (m, c) => m + (c -> (m.getOrElse(c, 0) + 1))
}

//scan 方法可以获得每一步中间结果集
(1 to 10).scanLeft(0)(_ + _)

//Vector(0, 1, 3, 6, 10, 15, 21, 28, 36, 45, 55)

