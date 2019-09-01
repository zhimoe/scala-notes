import io.lettuce.core.RedisURI
import io.lettuce.core.cluster.RedisClusterClient
import io.lettuce.core.cluster.api.sync.RedisAdvancedClusterCommands

import scala.collection.JavaConverters._

val node1: RedisURI = RedisURI.create("localhost", 6379)
val node2: RedisURI = RedisURI.create("localhost", 6379)
val node3: RedisURI = RedisURI.create("localhost", 6379)

def using(client: RedisClusterClient)
         (fun: RedisAdvancedClusterCommands[String, String] => Unit): Unit = {
  val con = client.connect()
  try {
    val cmds = con.sync()
    fun(cmds)
  } finally {
    con.close()
    client.shutdown()
  }
}

def redis = using(RedisClusterClient.create(List(node1, node2, node3).asJava)) _

//=====================user script start=============================

redis { cmds =>
  val s = cmds.del("KEY:9000000002")
  println(s)
}



