package models

import play.api.libs.json.{Format, Json}

case class StatsResponse(urlsCount: Long, requestsCount: Long)

object StatsResponse{
  implicit lazy val statsResponse: Format[StatsResponse] = Json.format[StatsResponse]
}

