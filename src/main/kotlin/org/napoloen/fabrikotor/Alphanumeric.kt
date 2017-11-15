package org.napoloen.fabrikotor

import java.util.Random

class Alphanumeric {

  private val random = Random()

  fun randomInt(): Int = random.nextInt(1000)

  fun randomInt(max: Int): Int = random.nextInt(max)

  fun randomInt(min: Int, max: Int): Int = random.nextInt(max - min) + min

  fun randomIntsRange(min: Int, max: Int, stepValue: Int): List<Int> {
    if (stepValue <= 0) throw IllegalArgumentException("Step should be more then 0")
    return (min .. max).step(stepValue).toList()
  }

  fun randomLong(): Long = random.nextLong()

  fun randomLong(max: Long): Long = randomLong(2147483648L, max)

  fun randomLong(min: Long, max: Long): Long = min + (Math.random() * (max - min)).toLong()

  fun randomLongsRange(min: Long, max: Long, stepValue: Long): List<Long> {
    if (stepValue <= 0) throw IllegalArgumentException("Step should be more then 0")
    return (min .. max).step(stepValue).toList()
  }

  fun randomDouble(): Double = random.nextDouble()

  fun randomDouble(max: Double): Double = randomDouble(Double.MIN_VALUE, max)

  fun randomDouble(min: Double, max: Double): Double = min + random.nextDouble() * (max - min)

  fun randomDoublesRange(min: Double, max: Double, stepValue: Double): List<Double> {
    if (stepValue <= 0) throw IllegalArgumentException("Step should be more then 0")
    return (min .. max).step(stepValue).toList()
  }

  fun randomFloat(): Float = random.nextFloat()

  fun randomFloat(max: Float): Float = randomFloat(0f, max)

  fun randomFloat(min: Float, max: Float): Float = min + random.nextFloat() * (max - min)

  fun randomFloatsRange(min: Float, max: Float, stepValue: Float): List<Float> {
    if (stepValue <= 0) throw IllegalArgumentException("Step should be more then 0")
    return (min .. max).step(stepValue).toList()
  }

  fun randomGausian(): Double = random.nextGaussian()

  fun randomBoolean(): Boolean = random.nextBoolean()

  fun randomString(): String = string("0123456789abcefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ-_", 30)

  fun randomString(length: Int): String = randomString("0123456789abcefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ-_", length)

  fun randomString(charSeq: String, max: Int): String = string(charSeq, max)

  fun randomStrings(): List<String> = randomStrings(5, 100, 100)

  fun randomStrings(minLength: Int, maxLength: Int, amount: Int): List<String> {
    if (minLength != maxLength )
      return (1 .. amount).map { i -> randomString(randomInt(minLength, maxLength)) }
    else
      return (1 .. amount).map { i -> randomString(minLength) }
  }

  fun randomHash(): String = string("0123456789abcdef", 40)

  fun randomHash(length: Int): String = string("0123456789abcdef", length)

  fun randomHashList(): List<String> = randomHashList(40,40, 100)

  fun randomHashList(minLength: Int, maxLength: Int, amount: Int): List<String> {
    if (minLength != maxLength )
      return (1 .. amount).map { i -> randomHash(randomInt(minLength, maxLength)) }
    else
      return (1 .. amount).map { i -> randomHash(minLength) }
  }

  fun randomGuid(): String = randomGuid(5)

  fun randomGuid(version: Int): String {
    val guidPool = "abcdef1234567890"
    val variantPool = "ab89"
    return string(guidPool, 8) + "-" +
        string(guidPool, 4) + "-" +
        // The Version
        version +
        string(guidPool, 3) + "-" +
        // The Variant
        string(variantPool, 1) +
        string(guidPool, 3) + "-" +
        string(guidPool, 12)
  }

  fun randomGuidList(): List<String> = (1 .. 100).map { i -> randomGuid(5)}

  fun randomGuidList(version: Int, amount: Int): List<String> = (1 .. amount).map { i -> randomGuid(version) }

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

  infix fun ClosedRange<Double>.step(step: Double): Iterable<Double> {
    require(start.isFinite())
    require(endInclusive.isFinite())
    require(step > 0.0) { "Step must be positive, was: $step." }
    val sequence = generateSequence(start) { previous ->
      if (previous == Double.POSITIVE_INFINITY) return@generateSequence null
      val next = previous + step
      if (next > endInclusive) null else next
    }
    return sequence.asIterable()
  }

  infix fun ClosedRange<Float>.step(step: Float): Iterable<Float> {
    require(start.isFinite())
    require(endInclusive.isFinite())
    require(step > 0.0) { "Step must be positive, was: $step." }
    val sequence = generateSequence(start) { previous ->
      if (previous == Float.POSITIVE_INFINITY) return@generateSequence null
      val next = previous + step
      if (next > endInclusive) null else next
    }
    return sequence.asIterable()
  }
}