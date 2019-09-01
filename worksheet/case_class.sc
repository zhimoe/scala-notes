// 5个特性
// 1 添加companion object,apply方法,unapply方法
// 2 toString, hashCode and equals and copy methods
case class Student(name: String, marks: Int)

val s1 = Student("Rams", 550)
val s2 = s1.copy()
val s3 = s1.copy(marks = 590)
s2 == s1 //true
s3 == s1 //false

// 3 构造函数参数自动成为成员变量,即自动给构造参数添加val前缀
// 4 模式匹配
// 5 默认的,case class和case object是可序列化的(实现Serializable),也即是可以网络传输的




