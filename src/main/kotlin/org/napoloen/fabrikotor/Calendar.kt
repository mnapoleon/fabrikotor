package org.napoloen.fabrikotor

import org.joda.time.DateTime
import org.joda.time.IllegalFieldValueException
import kotlin.random.Random

class Calendar(private val alphanumeric: Alphanumeric,
               private val random: Random,
               private val utilityService: UtilityService) {


    fun ampm():String {
        val randomVal = alphanumeric.randomInt(0,10)
        return if (randomVal < 5) "am" else "pm"
    }

    fun day(): String = day(year().toInt(), month()?.toInt() ?: 0, 1, 31)

    fun day(year: Int, month: Int): String = day(year, month, 1, 31)

    private fun day(year: Int, month: Int, min: Int, max: Int): String {
        if ((min <=0 || max >= 31) || (min >= 31 || max <= 0)) throw IllegalArgumentException("min and max values should be in [1,31] range")
        var result = ""
        var dayValue = 0
        while(result.equals("")) {
            try {
                dayValue = alphanumeric.randomInt(min, max)
                val date = DateTime(year, month, dayValue, 0, 0)
                result = dayValue.toString()
            }
            catch(e: IllegalFieldValueException) {

            }
        }
        return result
    }

    fun dayOfWeek(): String = utilityService?.getValueFromJsonArray("day_of_week") ?: ""

    fun time24h(): String = hour24h() + ":" + minute()

    fun time12h(): String = hour12h() + ":" + minute()

    fun hour24h(): String = alphanumeric.randomInt(0,24).toString()

    fun hour12h(): String = alphanumeric.randomInt(0, 12).toString()

    fun second(): String = alphanumeric.randomInt(0, 59).toString()

    fun minute(): String = alphanumeric.randomInt(0, 59).toString()

    fun month(): String? = month(asNumber = true)

    private fun month(asNumber: Boolean): String? =
        if (asNumber) alphanumeric.randomInt(1, 12).toString()
        else utilityService.getValueFromJsonArray("month")

    fun year(): String = alphanumeric.randomInt(1970, 2055).toString()

    fun century(): String? = utilityService.getValueFromJsonArray("centuries")
}