package services

import models.UrlShortenerResponse

object UrlService {
  def addUrl(url: String):  Unit={

  }

  def lookUpUrl(url: String): Option[UrlShortenerResponse] ={
    Some(UrlShortenerResponse(url, "", ""))
  }
}