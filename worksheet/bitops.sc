def toInt(buffer: Array[Byte], offset: Int) = {
  var values = 0
  for (i <- 0 until 4) {
    values <<= 8
    values |= (buffer(offset + i) & 0xff)
  }
  values
}

val res = toInt(Array('1', '0', '0', '0'), 0)

var i = 0
i <<= 8
var r = 7 & 0xff