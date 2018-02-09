package controllers

import services.Couchbase


import javax.inject._
import scala.concurrent.ExecutionContext
import org.reactivecouchbase.rs.scaladsl.json._
import play.api.mvc._
import akka.stream.Materializer
import play.api.libs.json._

import scala.collection.immutable.StringOps


import org.reactivecouchbase.rs.scaladsl.{N1qlQuery, ReactiveCouchbase}

@Singleton
class CouchbaseController @Inject()(cc: ControllerComponents, couchbase: Couchbase)
    (implicit ec: ExecutionContext, materializer: Materializer) extends AbstractController(cc) {

  def testBucket = couchbase.bucket("test")

  def read() = Action.async {
     // Pick a random document to retrieve and form a key
     val r = new scala.util.Random
     val r1 = r.nextInt(1000000)
     //     Console.println("rand: " + "%020d".format(r1))
 
     val k = "user::" + "%020d".format(r1)


     val value = testBucket.get(k)
     value.map(i => Ok(Json.toJson(i)))
  }
}
