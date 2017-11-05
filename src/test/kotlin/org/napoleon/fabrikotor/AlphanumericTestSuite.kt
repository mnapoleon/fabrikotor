package org.napoleon.fabrikotor

import org.testng.annotations.*

class AlphanumericTestSuite : BaseTestSuite() {

  @DataProvider(name = "numerifyDP")
  fun numerifyDP():Array<Array<String>> {
    return arrayOf(
        arrayOf("###ABC", "\\d{3}\\w{3}"),
        arrayOf("###ABC###", "\\d{3}\\w{3}\\d{3}"),
        arrayOf("ABC###", "\\w{3}\\d{3}"),
        arrayOf("A#B#C#", "\\w{1}\\d{1}\\w{1}\\d{1}\\w{1}\\d{1}"),
        arrayOf("ABC", "\\w{3}"),
        arrayOf("154,#$$%ABC", "\\d{3}\\W{1}\\d{1}\\W{3}\\w{3}"))
  }

  @Test(dataProvider = "numerifyDP")
  fun testNumerify(value: String, matchPattern: String) {
    val result = alpha.numerify(value)
    val regex = Regex(matchPattern)
    assert(result.matches(regex))
  }

  @Test
  fun testNumerifyList() {
    val list = alpha.numerifyList("###ABC", 10)
    assert(list.size == 10)
    list.forEach { x -> assert(x.matches(Regex("\\d{3}\\w{3}"))) }
  }

  @DataProvider(name = "letterifyDP")
  fun letterifyDP():Array<Array<String>> {
    return arrayOf(
        arrayOf("???123", "\\w{3}\\d{3}"),
        arrayOf("???123???", "\\w{3}\\d{3}\\w{3}"),
        arrayOf("123???", "\\d{3}\\w{3}"),
        arrayOf("1?2?3?", "\\d{1}\\w{1}\\d{1}\\w{1}\\d{1}\\w{1}"),
        arrayOf("123", "\\d{3}"),
        arrayOf("154,??$$%123", "\\d{3}\\W{1}\\w{2}\\W{3}\\d{3}")
    )
  }

  @Test(dataProvider = "letterifyDP")
  fun testLetterify(value: String, matchPattern: String) {
    val result = alpha.letterify(value)
    val regex = Regex(matchPattern)
    assert(result.matches(regex))
  }

  @Test
  fun testLetterifyList() {
    val list = alpha.letterifyList("???123", 10)
    assert(list.size == 10)
    list.forEach { x -> assert(x.matches(Regex("\\w{3}\\d{3}"))) }
  }

  @Test
  fun testDefaultInteger() {
    val integer = alpha.randomInt()
    //if (debugEnabled) logger.debug("Checking default integer function. Should return random integer below 1000 : " + integer)
    assert(integer in (0 .. 1000))
    assert(integer is Int)
  }

  @Test
  fun testDefaultLong() {
    val longNumber = alpha.randomLong()
    //if (debugEnabled) logger.debug("Checking default longNumber function. Should return random longNumber below 1000 : " + longNumber)
    assert(longNumber is Long)
  }

  @Test
  fun testDefaultDouble() {
    val doubleNumber = alpha.randomDouble()
    assert(doubleNumber is Double)
  }

  @Test
  fun testDefaultFloat() {
    val float = alpha.randomFloat()
    //if (debugEnabled) logger.debug("Checking default float function. Should return random float below 1000 : " + float)
    assert(float > 0 && float < 1000)
    assert(float is Float)
  }

  @Test
  fun testDefaultGausian() {
    val gausian = alpha.randomGausian()
    //if (debugEnabled) logger.debug("Checking default gausian function. Should return random gausian below 1000 : " + gausian)
    assert(gausian < 1000)
    assert(gausian is Double)
  }

  @Test
  fun testDefaultBoolean() {
    var trueCount = 0
    var falseCount = 0
    for (i in 0 .. 100) {
      val boolean = alpha.randomBoolean()
      if (boolean) trueCount += 1
      else falseCount += 1
    }
    assert(trueCount > 0 && falseCount > 0)
  }

  @Test
  fun testDefaultString() {
    val string = alpha.randomString()
    //if (debugEnabled) logger.debug("Checking default string function. Should return random string below 30 : " + string)
    assert(string.length == 30)
    assert(string is String)
  }
}