package org.napoleon.fabrikotor

import org.testng.annotations.*

class CalendarTestSuite: BaseTestSuite() {

    private val daysOfWeekList = util.getArrayFromJson("day_of_week")

    @Test
    fun testAmPm() {
        var amCount = 0
        var pmCount = 0
        for (i in 1 .. 50){
            val ampm = calendar.ampm()
            if (ampm == "am") amCount += 1 else pmCount += 1
        }
        assert(amCount > 0 && pmCount > 0)
    }

    @Test
    fun testSecond() {
        val second = calendar.second()
        assert(second.toInt() in 0..59)
    }

    @Test
    fun testMinute() {
        val minute = calendar.minute()
        assert(minute.toInt() in 0 .. 59)
    }

    @Test
    fun testHour() {
        val hour24 = calendar.hour24h()
        val hour12 = calendar.hour12h()
        assert(hour24.toInt() in 0 .. 23)
        assert(hour12.toInt() in 0 .. 11)
    }

    @Test
    fun testDefaultDay() {
        val day = calendar.day().toInt()
        print("DAY is $day")
        assert(day in 1 .. 31)
    }

    @Test
    fun testDay() {
        val year = calendar.year()
        val month = calendar.month()
        val day = calendar.day(year.toInt(), month?.toInt()?:0)
        assert(day.toInt() in 0 .. 31)
    }

    @Test
    fun testDayOfWeek() {
        val dayOfWeek = calendar.dayOfWeek()
        assert(daysOfWeekList?.contains(dayOfWeek) ?: false)
    }

}