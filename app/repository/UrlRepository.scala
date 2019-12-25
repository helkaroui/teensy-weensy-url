package repository

trait UrlRepository {
  def addURL(id: String, data: String)
}
