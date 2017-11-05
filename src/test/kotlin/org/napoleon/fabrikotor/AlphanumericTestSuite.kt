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
}