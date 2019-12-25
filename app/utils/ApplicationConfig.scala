package utils

import com.typesafe.config.{ConfigFactory, Config => TypesafeConfig}

object ApplicationConfig {
  val AppConfig: TypesafeConfig = ConfigFactory.load()

  val Redis: TypesafeConfig = AppConfig.getConfig("redis")
  object RedisConfig{
    val host: String = Redis.getString("host")
    val port: Int = Redis.getInt("port")
  }

  val Common: TypesafeConfig = AppConfig.getConfig("common")
  object CommonConfig{
    val salt: String = Common.getString("salt")
    val domain: String = Common.getString("short-url-domain")
  }
}
