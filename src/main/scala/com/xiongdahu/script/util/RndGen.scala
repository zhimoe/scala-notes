package com.xiongdahu.script.util

import java.time.{LocalDate, LocalDateTime}
import java.time.format.DateTimeFormatter
import scala.collection.mutable.ArrayBuffer
import scala.util.Random

/**
 * 随机生成器
 * 数值类的生成请使用Scala.util.Random的nextInt相关方法
 * String类的生成
 * Author:xiongdahu
 */
case class RndGen(private val random: Random = new Random()) {
  def randomInt(): Int = random.nextInt(1000)

  def randomInt(max: Int): Int = random.nextInt(max)

  def randomInt(min: Int, max: Int): Int = return random.nextInt(max - min) + min

  /**
   * ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789
   *
   * @param len
   * @return
   */
  def randomID(len: Int): String = {
    Random.alphanumeric.take(len).mkString("")
  }

  def randomHan(): String = {
    val highCode = (176 + Math.abs(random.nextInt(39)))
    val lowCode = (161 + Math.abs(random.nextInt(93)))
    val bytes = Array[Byte](highCode.toByte, lowCode.toByte)
    new String(bytes, "GBK")
  }

  def randomHans(len: Int): String = {
    val buf = ArrayBuffer[String]()
    for (i <- 0 to len) {
      buf.+=(randomHan())
    }
    buf.mkString
  }

  def randomDate(): String = {
    val days = random.nextInt(100)
    val rndDate = LocalDate.now().plusDays(days)
    rndDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
  }

  def randomDate(from: String, maxPlusDays: Int): String = {
    val fromDt = LocalDate.parse(from)
    val rndDate = fromDt.plusDays(randomInt(maxPlusDays))
    rndDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
  }

  def randomDateTime(): String = {
    val days = random.nextInt(100)
    val rndDate = LocalDateTime.now().plusDays(days)
    rndDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))

  }


  def randomTimeStamp(): String = {
    val days = random.nextInt(100)
    val rndDate = LocalDateTime.now()
    rndDate.format(DateTimeFormatter.ofPattern("HH:mm:ss.SSSSSS"))
  }

  def randomDigits(len: Int): String = {
    val stream = Random.alphanumeric
    stream.filter(_.isDigit).take(len).mkString
  }

}

