package repository

import com.redis._
import utils.ApplicationConfig.RedisConfig

object RedisUrlRepository extends UrlRepository {
  val URL_KEY_PREFIX = "url:"
  val URL_COUNTER_KEY_PREFIX = "url-counter:"

  private lazy val clients = new RedisClientPool(RedisConfig.host, RedisConfig.port)

  override def setURL(shortUrl: String, originalUrl: String): Boolean = {
    val key = URL_KEY_PREFIX + shortUrl
    clients.withClient(_.setnx(key, originalUrl))
  }

  override def getURL(shortUrl: String): Option[String] = {
    val key = URL_KEY_PREFIX + shortUrl
    clients.withClient {
      client => client.get[String](key)
    }
  }

  override def getNextId: Option[Long] = {
    val key = URL_COUNTER_KEY_PREFIX
    clients.withClient(_.incr(key))
  }
}
