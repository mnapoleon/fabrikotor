package org.napoloen.fabrikotor

import kotlin.random.Random

object Fabrikotor {

    fun alphaNumeric(): Alphanumeric {
        return Alphanumeric()
    }

    fun calendar(): Calendar {
        return Calendar(Alphanumeric(), Random.Default, UtilityService())
    }

    fun calendar(locale:String): Calendar {
        return Calendar(Alphanumeric(), Random.Default, UtilityService(locale))
    }

}