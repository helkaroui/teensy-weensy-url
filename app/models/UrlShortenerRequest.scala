package models

import play.api.libs.json.{Format, Json}

case class UrlShortenerRequest(url: String)

object UrlShortenerRequest{
  implicit lazy val urlShortenerRequest: Format[UrlShortenerRequest] = Json.format[UrlShortenerRequest]
}
