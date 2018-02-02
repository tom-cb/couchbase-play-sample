package controllers

import services.Couchbase


import javax.inject._
import scala.concurrent.ExecutionContext
import org.reactivecouchbase.rs.scaladsl.json._
import play.api.mvc._
import akka.stream.Materializer
import play.api.libs.json._


import org.reactivecouchbase.rs.scaladsl.{N1qlQuery, ReactiveCouchbase}

@Singleton
class CouchbaseController @Inject()(cc: ControllerComponents, couchbase: Couchbase)
    (implicit ec: ExecutionContext, materializer: Materializer) extends AbstractController(cc) {

  def testBucket = couchbase.bucket("test")

  def read() = Action.async {
      val value = testBucket.get("key1")
     value.map(i => Ok(Json.toJson(i)))
  }
}
