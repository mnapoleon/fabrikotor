package org.napoloen.fabrikotor

import java.util.Random

class Alphanumeric {

  private val random = Random()

  fun randomInt(): Int = random.nextInt(1000)

  fun randomLong(): Long = random.nextLong()

  fun randomDouble(): Double = random.nextDouble()

  fun randomFloat(): Float = random.nextFloat()

  fun randomGausian(): Double = random.nextGaussian()

  fun randomBoolean(): Boolean = random.nextBoolean()

  fun randomString(): String = string("0123456789abcefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ-_", 30)

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

  private fun string(charSequence: CharSequence, max: Int):String {
    val builder = StringBuilder()
    (1 .. max).forEach { it ->
      builder.append(charSequence[random.nextInt(charSequence.length - 1)])
    }
    return builder.toString()
  }
}