package zhi.moe.script.leet

object Solution {
  def toLowerCase(str: String): String = {
    str.toLowerCase()
  }

  def reverseWords(s: String): String = {
    val res = new StringBuilder()
    var word = ""
    for (c <- s) {
      c match {
        case ' ' => {
          res.append(word.reverse + " ")
          word = ""
        }
        case _ => word += c
      }
    }

    if (!word.isEmpty) {
      res.append(word.reverse)
    }

    return res.toString().trim
  }

  def validIPAddress(IP: String): String = {
    if (IP.contains(".")) {
      val seg = IP.split("\\.", -1)
      if (seg.length != 4) return "Neither"

      for (s <- seg) {
        if (!s.forall(_.isDigit)) return "Neither"
        if (s.isEmpty || (s.startsWith("0") && s.length != 1) || s.length > 3 || s.toInt > 255) return "Neither"
      }

      return "IPv4"
    } else if (IP.contains(":")) {

      val set = Set('1', '2', '3', '4', '5', '6', '7', '8', '9', '0', 'a', 'b', 'c', 'd', 'e')
      val seg = IP.split(":", -1)
      if (seg.length != 8) return "Neither"
      for (s <- seg) {
        if (s.isEmpty || s.length > 4 || (s.forall(_.isDigit) && s.length != 1 && s.toInt == 0)) return "Neither"

        for (c <- s.toLowerCase()) {
          if (!set.contains(c)) return "Neither"
        }
      }
      return "IPv6"
    }

    "Neither"
  }


  def main(args: Array[String]): Unit = {

    assert("s'teL ekat edoCteeL tsetnoc" == reverseWords("Let's take LeetCode contest"))
    assert("IPv4" == validIPAddress("172.16.254.1"))
    assert("Neither" == validIPAddress("256.256.256.256"))
    assert("Neither" == validIPAddress("2001:0db8:85a3:0:0:8A2E:0370:7334:"))
    assert("IPv6" == validIPAddress("2001:0db8:85a3:0:0:8A2E:0370:7334"))
    assert("IPv6" == validIPAddress("2001:0db8:85a3:0000:0:8A2E:0370:733a"))
    assert("Neither" == validIPAddress("1e1.4.5.6"))

  }


}

