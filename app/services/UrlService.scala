package services

import org.pico.hashids._
import models.UrlShortenerResponse
import repository.RedisUrlRepository

import utils.ApplicationConfig.{CommonConfig => AppConfig}

trait UrlService {
  def addUrl(url: String): UrlShortenerResponse
  def lookUpUrl(shortUrl: String): Option[UrlShortenerResponse]
}

object UrlService extends UrlService {
  private lazy val hashIds = Hashids.reference(AppConfig.salt)

  private[services] def generateKey(counter: Long): String ={
    hashIds.encode(counter)
  }

  private[services] def reverseKey(key: String): Option[Long]  ={
    hashIds.decode(key).headOption
  }

  def addUrl(url: String):  UrlShortenerResponse={
    val index: Option[Long] = RedisUrlRepository.getNextId
    val shortUri = index match {
      case None => throw new Exception("No index returned by redis")
      case Some(id) => generateKey(id)
    }
    RedisUrlRepository.setURL(shortUri, url)

    val shortUrl = AppConfig.domain + "/" + shortUri
    UrlShortenerResponse(url, shortUrl)
  }

  def lookUpUrl(shortUri: String): Option[UrlShortenerResponse] ={
    RedisUrlRepository.getURL(shortUri).map(originalUrl => UrlShortenerResponse(originalUrl, AppConfig.domain + "/" + shortUri))
  }
}

