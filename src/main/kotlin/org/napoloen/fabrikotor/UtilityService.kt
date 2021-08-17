package org.napoloen.fabrikotor

import com.beust.klaxon.JsonObject
import org.napoloen.fabrikotor.entities.RandomDataKeeper
import java.util.Random

class UtilityService(val lang: String = "us", private val random: Random = Random()) {

  val valuesJson:JsonObject = RandomDataKeeper.getJson(lang)

  fun isLess(a: Any, b: Any): Boolean =
    when {
      a is Int && b is Int -> a < b
      a is Double && b is Double -> a < b
      a is Float && b is Float ->  a < b
      a is Long && b is Long -> a < b
      else -> throw Exception("Invalid arguments were passed .They should be either int, double, float and both same type")
    }

  fun isLessOrEqual(a: Any, b: Any): Boolean =
    when {
      a is Int && b is Int -> a <= b
      a is Double && b is Double -> a <= b
      a is Float && b is Float ->  a <= b
      a is Long && b is Long -> a <= b
      else -> throw Exception("Invalid arguments were passed .They should be either int, double, float and both same type")
    }

}