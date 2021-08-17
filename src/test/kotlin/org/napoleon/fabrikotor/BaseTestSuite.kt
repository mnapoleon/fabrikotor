package org.napoleon.fabrikotor

import org.napoloen.fabrikotor.*
import kotlin.random.Random

open class BaseTestSuite() {

    val util:UtilityService = UtilityService()
    val alpha:Alphanumeric = Alphanumeric()
    val calendar:Calendar = Calendar(Alphanumeric(), Random.Default, UtilityService())

}