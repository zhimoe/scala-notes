// ElasticSearch script

import com.sksamuel.elastic4s.http.ElasticDsl.search
import com.sksamuel.elastic4s.http.search.SearchResponse
import com.sksamuel.elastic4s.http.{ElasticClient, ElasticProperties, Response}
// you must import the DSL to use the syntax helpers
import com.sksamuel.elastic4s.http.ElasticDsl._

def using[A <: {def close() : Unit}, R](resources: A)(fun: A => R): R = {
  import scala.language.reflectiveCalls
  try {
    fun(resources)
  } finally {
    resources.close()
  }
}

//======user script
using(ElasticClient(ElasticProperties("http://localhost:9200"))) {
  client =>
    val response: Response[SearchResponse] = client.execute {
      search("testindex").matchQuery("entNm", "京东")
    }.await

    response.result.hits.hits.map {
      hit => println(s"""${hit.sourceAsString}  ${hit.score}""");
    }
}




