package zhi.moe.script.leet

object IntBreak {
  def integerBreak(n: Int): Int = {
    val dp: Array[Int] = Array.fill[Int](n + 1)(1)
    dp(1) = 1
    for (i <- 3 until (n + 1)) {
      for (j <- 1 until i) {
        dp(i) = scala.math.max(dp(i), scala.math.max(j * dp(i - j), j * (i - j)))
      }
    }
    dp(n)
  }


  def numDecodings(s: String): Int = {
    if (s == null || s.length == 0 || s(0) == '0') return 0

    val dp = Array.fill[Int](s.length + 1)(0)
    dp(0) = 1

    for (i <- 1 until s.length) {
      //      var one = s.substring()
    }
    1
  }


  def restoreString(s: String, indices: Array[Int]): String = {
    val res = Array.fill[Char](s.length)(0)
    for (i <- 0 until s.length) {
      res(indices(i)) = s(i)
    }
    res.mkString
  }

  def main(args: Array[String]): Unit = {
    println(restoreString("leet", Array(2, 3, 0, 1)))
    assert(restoreString("leet", Array(2, 3, 0, 1)) == "etle")

  }
}

