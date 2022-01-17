package org.napoloen.fabrikotor

import kotlin.random.Random

class Calendar(private val alphanumeric: Alphanumeric,
               private val random: Random,
               private val utilityService: UtilityService) {


    fun ampm():String {
        val randomVal = alphanumeric.randomInt(0,10)
        return if (randomVal < 5) "am" else "pm"
    }

    fun dayOfWeek(): String = utilityService?.getValueFromJsonArray("day_of_week") ?: ""
}