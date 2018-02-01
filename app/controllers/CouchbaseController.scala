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
class ApiController @Inject()(couchbase: Couchbase)
    (implicit ec: ExecutionContext, materializer: Materializer) extends Controller {

  def testBucket = couchbase.bucket("test")

  def storeAndRead() = Action {
    testBucket 
      .insert[JsValue]("key1", Json.obj("message" -> "Hello World", "type" -> "doc"))

    val source = testBucket
      .get("key1")
      .map(i => Console.println("out: " + i))

    Console.println("type: " + source.getClass)
    Console.println("content: " + source)

    Ok("AOK")
  }
}
