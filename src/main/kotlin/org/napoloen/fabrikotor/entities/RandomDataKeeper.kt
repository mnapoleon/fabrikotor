package org.napoloen.fabrikotor.entities

import com.beust.klaxon.JsonObject
import com.beust.klaxon.Parser
import io.github.classgraph.*
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
            randomDataStorage[fileName.split("\\.")[0]] = parseFile(fileName) as JsonObject
        }
    }

    private fun parseFile(fileName: String): Any? {
        val cls = Parser::class.java
        return cls.getResourceAsStream(fileName)?.let { inputStream ->
            return Parser.default().parse(inputStream)
        }
    }

    fun getJson(locale: String = "us"): JsonObject {
        if (randomDataStorage.isEmpty()) {
            parseLanguageDateFiles()
        }
        return randomDataStorage.getOrElse(locale, throw IllegalArgumentException("You're trying to load $locale language files that don't exist"))
    }
}