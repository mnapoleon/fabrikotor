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
  fun testMaxInteger() {
    val testMaxValue = 100
    val integer = alpha.randomInt(testMaxValue)
    assert(integer in (0 .. testMaxValue))
    assert(util.isLess(integer, testMaxValue))
    assert(integer is Int)
  }

  @Test
  fun testDefaultLong() {
    val longNumber = alpha.randomLong()
    //if (debugEnabled) logger.debug("Checking default longNumber function. Should return random longNumber below 1000 : " + longNumber)
    assert(longNumber is Long)
  }

  @Test
  fun testMaxLong() {
    val testMaxValue = 100000000000L
    val longValue = alpha.randomLong(testMaxValue)
    assert(longValue in (0 .. testMaxValue))
    assert(util.isLess(longValue, testMaxValue))
    assert(longValue is Long)
  }

  @Test
  fun testDefaultDouble() {
    val doubleNumber = alpha.randomDouble()
    assert(doubleNumber is Double)
  }

  @Test
  fun testMaxDouble() {
    val testMaxValue = 5600.01234
    val doubleValue = alpha.randomDouble(testMaxValue)
    assert(util.isLess(doubleValue, testMaxValue))
    assert(doubleValue is Double)
  }

  @Test
  fun testDefaultFloat() {
    val float = alpha.randomFloat()
    //if (debugEnabled) logger.debug("Checking default float function. Should return random float below 1000 : " + float)
    assert(float > 0 && float < 1000)
    assert(float is Float)
  }

  @Test
  fun testMaxFloat() {
    val testMaxValue = 5600.01234f
    val floatValue = alpha.randomFloat(testMaxValue)
    assert(util.isLess(floatValue, testMaxValue))
    assert(floatValue is Float)
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

  @Test
  fun testCustomString() {
    val extendedString = alpha.randomString(50)
    //if (debugEnabled) logger.debug("Checking default extendedString function. Should return random extendedString below 50 : " + extendedString)
    assert(extendedString.length == 50)
    assert(extendedString is String)
  }

  @Test
  fun testDefaultStringsList() {
    val strings = alpha.randomStrings()
    assert(strings.size == 100)
    strings.forEach{string -> assert(string.length >= 5 && string.length <= 100)}
  }

  @Test
  fun testCustomStringsList() {
    val strings = alpha.randomStrings(10, 10, 20)
    assert(strings.size == 20)
    strings.forEach{string -> assert(string.length >= 10 && string.length <= 10)}
  }

  @DataProvider(name = "charSets")
  fun charSets():Array<Array<Any>> {
    return arrayOf(
        arrayOf("aaaa", 10),
        arrayOf("1234567890", 100),
        arrayOf("0123456789abcefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ-_", 500),
        arrayOf("!@#$%^&*()_+{}\"|:?><", 30)
    )
  }

  @Test(dataProvider = "charSets")
  fun testCustomStringWithSpecificCharSet(charSet: String, max: Int) {
    val string = alpha.randomString(charSet, max)
    assert(string.length == max)
    string.forEach{ symbol -> assert(charSet.contains(symbol)) }
  }

  @DataProvider(name = "numbersRandomRange")
  fun numbersRandomRange(): Array<Array<out Any>> {
    return arrayOf(
        arrayOf(100, 150),
        arrayOf(100.10, 200.01),
        arrayOf(100.10f, 250.10f),
        arrayOf(1L, 100L)
    )
  }

  @Test(dataProvider = "numbersRandomRange")
  fun testNumbersRandomRange(min: Any, max: Any) {

    fun calculate(minValue: Any, maxValue: Any): Any =
      when {
        minValue is Int && maxValue is Int -> alpha.randomInt(minValue, maxValue)
        minValue is Double && maxValue is Double -> alpha.randomDouble(minValue, maxValue)
        minValue is Float && maxValue is Float -> alpha.randomFloat(minValue, maxValue)
        minValue is Long && maxValue is Long -> alpha.randomLong(minValue, maxValue)
        else -> "Not supported"
      }
    val actualNumber = calculate(min, max)
    //if (debugEnabled) logger.debug("Checking random number in range: " + actualNumber)
    assert(actualNumber.javaClass ==  min.javaClass)
    assert(util.isLess(actualNumber, max))
    assert(util.isLessOrEqual(min, actualNumber))
  }

  @DataProvider(name = "integerRangeWithStep")
  fun integerRangeWithStep(): Array<Array<out Any>> {
    return arrayOf(
        arrayOf(1, 10, 1, listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)),
        arrayOf(1, 10, 2, listOf(1, 3, 5, 7, 9)),
        arrayOf(1, 10, 0, listOf(1, 3, 5, 7, 9)),
        arrayOf(1, 10, -1, listOf(1, 3, 5, 7, 9))
    )
  }

  @Test(dataProvider = "integerRangeWithStep")
  fun testIntegerRangeWithStep(min: Int, max: Int, step: Int, expectedResult: List<Int>) {
    if (step <= 0) {
      try {
        alpha.randomIntsRange(min, max, step)
      } catch (e: IllegalArgumentException){
         assert(e.message == "Step should be more then 0")
      }
    } else {
      val generatedStream = alpha.randomIntsRange(min, max, step)
      assert(expectedResult == generatedStream)
    }
  }

  @DataProvider(name = "doubleRangeWithStep")
  fun doubleRangeWithStep(): Array<Array<out Any>> {
    return arrayOf(
        arrayOf(1, 10, 1, listOf(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0)),
        arrayOf(1, 10, 2, listOf(1.0, 3.0, 5.0, 7.0, 9.0)),
        arrayOf(1, 3, 0.5, listOf(1.0, 1.5, 2.0, 2.5, 3.0)),
        arrayOf(1, 3, -1, listOf(1.0, 1.5, 2.0, 2.5, -1.0)),
        arrayOf(1, 3, 0, listOf(1.0, 1.5, 2.0, 2.5, -1.0))
    )
  }

  @Test(dataProvider = "doubleRangeWithStep")
  fun testDoubleRangeWithStep(min: Double, max: Double, step: Double, expectedResult: List<Double>) {
    if (step <= 0) {
      try {
        alpha.randomDoublesRange(min, max, step)
      } catch (e: IllegalArgumentException) {
        assert(e.message == "Step should be more then 0")
      }
    } else {
      val generatedStream = alpha.randomDoublesRange(min, max, step)
      assert(expectedResult == generatedStream)
    }
  }

  @DataProvider(name = "floatRangeWithStep")
  fun floatRangeWithStep(): Array<Array<Any>> {
    return arrayOf(
        arrayOf(1.0f, 10.0f, 1.0f, listOf(1.0f, 2.0f, 3.0f, 4.0f, 5.0f, 6.0f, 7.0f, 8.0f, 9.0f, 10.0f)),
        arrayOf(1.0f, 10.0f, 2.0f, listOf(1.0f, 3.0f, 5.0f, 7.0f, 9.0f)),
        arrayOf(1.0f, 3.0f, 0.5f, listOf(1.0f, 1.5f, 2.0f, 2.5f, 3.0f)),
        arrayOf(1.0f, 3.0f, 0.0f, listOf(1.0f, 1.5f, 2.0f, 2.5f, 3.0f)),
        arrayOf(1.0f, 3.0f, -1.0f, listOf(1.0f, 1.5f, 2.0f, 2.5f, 3.0f))
    )
  }

  @Test(dataProvider = "floatRangeWithStep")
  fun testFloatRangeWithStep(min: Float, max: Float, step: Float, expectedResult: List<Float>) {
    if (step <= 0) {
      try {
        alpha.randomFloatsRange(min, max, step)
      } catch (e: IllegalArgumentException) {
        assert(e.message == "Step should be more then 0")
      }
    } else {
      val generatedStream = alpha.randomFloatsRange(min, max, step)
      assert(expectedResult ==generatedStream)
    }
  }

  @DataProvider(name = "numbersCustomTypes")
  fun numbersCustomTypes(): Array<Array<out Any>> {
    return arrayOf(arrayOf(100, Integer::class),
        arrayOf(100.10, Double::class),
        arrayOf(100.10f, Float::class),
        arrayOf(100000000000L, Long::class)
    )
  }

  @Test(dataProvider = "numbersCustomTypes")
  fun testCustomNumberType(maxValue: Any, numberType: Any) {

    fun calculate(randomNumber: Any) =
      when (randomNumber){
        is Int -> alpha.randomInt(randomNumber)
        is Double -> alpha.randomDouble(randomNumber)
        is Float -> alpha.randomFloat(randomNumber)
        is Long -> alpha.randomLong(randomNumber)
        else -> "Type not supported"
      }

    val result = calculate(maxValue)
    //if (debugEnabled) logger.debug("Checking custom number with " + numberType + " type function. Should return with specific type and below specified value : ")
    assert(result::class == numberType)
    assert(util.isLess(result, maxValue))
  }

  @Test
  fun testHash() {
    val hash = alpha.randomHash()
    //if (debugEnabled) logger.debug("Checking random hash number with default length:  " + hash)
    assert(hash.length == 40)
    val customLengthHash = alpha.randomHash(10)
    //if (debugEnabled) logger.debug("Checking random hash number with length = 10:  " + customLengthHash)
    assert(customLengthHash.length == 10)
  }

  @Test
  fun testHashList() {
    val defaultList = alpha.randomHashList()
    assert(defaultList.size == 100)
    defaultList.forEach{x -> assert(x.length == 40)}
    val customList = alpha.randomHashList(20, 40, 30)
    assert(customList.size == 30)
    customList.forEach{x -> assert(x.length >= 20 && x.length <= 40)}
  }

}