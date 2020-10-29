package zhi.moe.script.typcls

trait Show[A] {
  def toHtml(a: A): String
}

object Show {
  def apply[A](implicit sh: Show[A]): Show[A] = sh

  object ops {
    def toHtml[A: Show](a: A) = Show[A].toHtml(a)

    implicit class ShowOps[A: Show](a: A) { // 惯例使用TypeCls+Ops
      def toHtml = Show[A].toHtml(a)
    }

  }

//  implicit val intCanShow: Show[Int] = int => s"<int>$int</int>"
//  implicit val stringCanShow: Show[String] = str => s"<string>$str</string>"
//

  import cats.Show
}

