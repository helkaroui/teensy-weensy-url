package models

import play.api.libs.json.{Format, Json}

case class UrlShortenerResponse(originalUrl: String, shortUrl: String, creationDate: String)

object UrlShortenerResponse{
  implicit lazy val urlShortenerResponse: Format[UrlShortenerResponse] = Json.format[UrlShortenerResponse]
}
