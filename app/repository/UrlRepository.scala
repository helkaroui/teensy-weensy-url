package repository

trait UrlRepository {
  def setURL(shortUrl: String, originalUrl: String): Boolean
  def getURL(shortUrl: String): Option[String]
  def getNextId: Option[Long]
  def incrementRequestCounter
}
