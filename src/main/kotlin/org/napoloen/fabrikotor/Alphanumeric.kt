package org.napoloen.fabrikotor

import java.util.Random

class Alphanumeric {

  private val random = Random()

  fun randomInt(): Int = random.nextInt(1000)

  fun randomLong(): Long = random.nextLong()

  fun randomDouble(): Double = random.nextDouble()

  fun numerify(pattern: String): String {
    return pattern.map { it ->
      when (it) {
        '#' -> random.nextInt(10).toString()
        else -> it
      }
    }.joinToString(separator = "" )
  }

  fun numerifyList(pattern: String, amount: Int): List<String> {
    return (1..amount).map { i -> numerify(pattern) }
  }

  fun letterify(pattern: String): String {
    val chars = (('a' .. 'z') + ('A' .. 'Z')).toList().toCharArray()
    return pattern.map { it ->
      when (it) {
        '?' -> chars[random.nextInt(chars.size)]
        else -> it
      }
    }.joinToString(separator = "")
  }

  fun letterifyList(pattern: String, amount: Int): List<String> {
    return (1..amount).map { i -> letterify(pattern) }
  }

}