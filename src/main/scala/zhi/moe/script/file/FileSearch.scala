package zhi.moe.script.file

// 解决string 非法转义字符问题
object FileSearch {

  import java.nio.charset.CodingErrorAction
  import scala.io.{Codec, Source}

  implicit val codec = Codec("UTF-8")
  codec.onMalformedInput(CodingErrorAction.REPLACE)
  codec.onUnmappableCharacter(CodingErrorAction.REPLACE)

  def main(args: Array[String]): Unit = {
    for (line <- Source.fromFile("./test.file").getLines()) {
      if (line.contains("ABC")) {
      }
    }
  }
}


