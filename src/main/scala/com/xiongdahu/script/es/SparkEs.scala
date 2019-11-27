package com.xiongdahu.script.es

import com.xiongdahu.script.util.RndGen
import com.xiongdahu.script.util.Timing._

import org.apache.spark.{SparkConf, SparkContext}
import scala.collection.mutable.ArrayBuffer
import org.elasticsearch.spark._ // add saveToEs methods

/**
 * https://www.elastic.co/guide/en/elasticsearch/hadoop/current/spark.html
 * https://www.elastic.co/guide/en/elasticsearch/hadoop/current/configuration.html
 * https://www.elastic.co/guide/en/elasticsearch/hadoop/6.2/security.html
 */
object SparkEs {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("spark-es").setMaster("local[48]") //测试一个最佳性能线程数
    conf.set("spark.streaming.stopGracefullyOnShutdown", "true")

    conf.set("es.index.auto.create", "true")
      .set("es.nodes", "127.0.0.1") //逗号隔开
      .set("es.port", "9200")
      .set("es.nodes.wan.only", "true") // data nodes 不可用时则设置这个
      .set("es.net.http.auth.user", "admin")
      .set("es.net.http.auth.pass", "pass")
    val sc = new SparkContext(conf)

    //    -------------------------------------------------
    val rndGen = RndGen() //随机生成器

    def doc(): Map[String, String] = {
      Map("name" -> rndGen.randomHan(),
        "age" -> rndGen.randomInt(10, 80).toString,
        "company" -> rndGen.randomHan())
    }

    val persons = ArrayBuffer[Map[String, String]]()

    timing {
      for (_ <- 1 to 1000) {
        persons += doc()
      }

      sc.makeRDD(persons)
        .saveToEs("index/doc") //pimp my lib with implicit conversion
    }
  }

}
