package org.napoloen.fabrikotor.entities

import com.beust.klaxon.JsonObject
import com.beust.klaxon.Parser
import io.github.classgraph.*
import org.napoloen.fabrikotor.UtilityService
import java.lang.IllegalArgumentException

object RandomDataKeeper {

    private val randomDataStorage = hashMapOf<String, JsonObject>()
    private const val dataPath = "data_files/"

    private fun getResourcesList(): Set<String> {
        val fileNames = ArrayList<String>()
        val scanResult = ClassGraph().acceptPaths("data_files").scan()
        scanResult
            .getResourcesWithExtension("json")
            .forEachByteArrayThrowingIOException { res: Resource, context: ByteArray ->
                fileNames += res.path.replace(dataPath, "")
            }
        scanResult.close()
        return fileNames.toSet()
    }

    private fun parseLanguageDateFiles() {
        val filesList = getResourcesList()
        for (fileName in filesList) {
            val abc = fileName.split(".")[0]
            randomDataStorage[abc] = parseFile(fileName) as JsonObject
        }
    }

    private fun parseFile(fileName: String): Any? {
        val iStream2 = this.javaClass.classLoader.getResourceAsStream(dataPath+fileName)
        val iStream = Parser::class.java.getResourceAsStream(dataPath + fileName)
        return if (iStream2 != null) {
            Parser.default().parse(iStream2)
        } else null
    }

    fun getJson(locale: String = "us"): JsonObject? {
        if (randomDataStorage.isEmpty()) {
            parseLanguageDateFiles()
        }
        print(locale)
        return randomDataStorage[locale]
        //randomDataStorage.getOrElse(locale, throw IllegalArgumentException("You're trying to load $locale language files that don't exist"))
    }
}