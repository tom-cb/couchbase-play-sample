package services

import javax.inject._
import play.api.inject.ApplicationLifecycle
import play.api.Configuration
import org.reactivecouchbase.rs.scaladsl._
import com.typesafe.config.ConfigFactory


@Singleton
class Couchbase @Inject()(configuration: Configuration, lifecycle: ApplicationLifecycle) {
  private val driver = ReactiveCouchbase(ConfigFactory.parseString(
    """
      |buckets {
      |  test {
      |    name = "test"
      |    password = "testpass"
      |    authentication {
      |      username = "test"
      |      password = "testpass"
      |    }
      |    hosts = ["127.0.0.1"]
      |  }
      |}
    """.stripMargin))

//  private val driver = ReactiveCouchbase(configuration.underlying.getConfig("reactivecouchbase"))

  def bucket(name: String): Bucket = driver.bucket(name)

  lifecycle.addStopHook { () =>
    driver.terminate()
  }
}
