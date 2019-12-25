package services

import org.scalatest.FunSuite

class UrlServiceTest extends FunSuite {

  test("testGenerateKey/reversekey") {
    val key = UrlService.generateKey(10L)
    val reversed = UrlService.reverseKey(key)
    assert(reversed.get === 10L)
  }

  test("testaddUrl") {

  }

}
