package org.napoleon.fabrikotor

import org.testng.annotations.*

class CalendarTestSuite: BaseTestSuite() {

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


}