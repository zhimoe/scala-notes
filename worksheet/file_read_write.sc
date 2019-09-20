import java.io.FileInputStream
import org.apache.commons.codec.binary.Base64
import scala.reflect.io.File
import scala.language.reflectiveCalls

val path = File(".").toAbsolute

def use[A <: {def close() : Unit}, R](resources: A)(fun: A => R): R = {
  try {
    fun(resources)
  } finally {
    resources.close()
  }
}

use(new FileInputStream("pic.jpg")) {
  fis =>
    val data = new Array[Byte](fis.available)
    fis.read(data)
    val b64Array = Base64.encodeBase64(data)
    println(new String(b64Array))
}

// best way read file as byte[]

import java.nio.file.{Files, Paths}

val byteArray: Array[Byte] = Files.readAllBytes(Paths.get("pic.jpg"))


