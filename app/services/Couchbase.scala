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
      |    hosts = ["ec2-34-213-160-226.us-west-2.compute.amazonaws.com"]
      |  }
      |}
    """.stripMargin))

//  private val driver = ReactiveCouchbase(configuration.underlying.getConfig("reactivecouchbase"))

  def bucket(name: String): Bucket = driver.bucket(name)

  lifecycle.addStopHook { () =>
    driver.terminate()
  }
}
