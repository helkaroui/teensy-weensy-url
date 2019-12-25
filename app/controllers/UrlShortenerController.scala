package controllers

import javax.inject._
import models.{UrlShortenerRequest, UrlShortenerResponse}
import play.api.libs.json.{Format, Json}
import play.api.mvc._
import services.UrlService

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class UrlShortenerController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {

  def generate() = Action { implicit request: Request[AnyContent] =>
    val body = request.body.asJson.map(_.as[UrlShortenerRequest]).getOrElse {
      throw new Exception("Missing required parameter: Body")
    }
    Ok(body.url)
  }

  def redirect(shortUrl: String) : Action[AnyContent]  = Action { implicit request: Request[AnyContent] =>
    UrlService.lookUpUrl(shortUrl) match {
      case None => BadRequest(s"Uri: $shortUrl not found in database.")
      case Some(value) => {
        val json = Json.toJson(value)
        Ok(json)
      }
    }
  }
}