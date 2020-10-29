package zhi.moe.script.util

object Timing {

  def timing[R](block: => R): R = {
    val t0 = System.nanoTime()
    val result = block // call-by-name
    val t1 = System.nanoTime()
    println(s"###Elapsed time: ${(t1 - t0) / 1000000L} ms")
    result
  }
}
