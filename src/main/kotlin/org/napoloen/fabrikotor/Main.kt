package org.napoloen.fabrikotor

import kotlin.random.Random

import com.beust.klaxon.*

fun main() {
    val raw = parse("/data_files/us.json") as JsonObject
    val utilityService = UtilityService()
    val centuries = utilityService.getArrayFromJson("centuries")
    //val calendar = raw.obj("calendar")

    println(getRandomArrayElement(centuries))
}

fun getRandomArrayElement(array: JsonArray<String>?): String {
    val randomIndex = Random.nextInt(array?.size ?: 0)
    return array?.get(randomIndex).toString()

}

fun parse(name: String): Any? {
    val cls = Parser::class.java
    return cls.getResourceAsStream(name)?.let { inputStream ->
        return Parser.default().parse(inputStream)
    }
}