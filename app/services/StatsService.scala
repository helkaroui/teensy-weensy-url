package services

import models.{StatsResponse, UrlShortenerResponse}
import repository.RedisUrlRepository

trait StatsService {
  def getStats: StatsResponse
}

object StatsService extends StatsService {

  def incrementRequestsCounter(): Unit = {
    RedisUrlRepository.incrementRequestCounter
  }

  override def getStats: StatsResponse = {
    val urlsCount: Option[Long] = RedisUrlRepository.getUrlCount
    val requestsCount: Option[Long] = RedisUrlRepository.getRequestCount
    StatsResponse(
      urlsCount = urlsCount.getOrElse(0),
      requestsCount= requestsCount.getOrElse(0)
    )
  }
}



