package fr.pierreberton.adventofcode.day4

import java.io.File
import java.nio.file.Files
import java.util.*
import kotlin.collections.ArrayList

class Exo1 {
    companion object {
        @JvmStatic
        fun main(vararg args: String) {

            val passports = ArrayList<MutableMap<FieldType, String>>()
            passports.add(EnumMap(FieldType::class.java))

            Files.readAllLines(File(Exo1::class.java.getResource("./day4.input").path).toPath())
                .forEach { line ->
                    if (line.isBlank()) {
                        passports.add(EnumMap(FieldType::class.java))
                    } else {
                        line.replace("""\s\s+""".toRegex(), " ").trim().split(" ").forEach {
                            val attrParts = it.split(":")
                            passports.last()[FieldType.valueOf(
                                attrParts[0].toUpperCase()
                            )] = attrParts[1]
                        }
                    }
                }

            val nbValidPassports = passports.filter {
                (it.keys.size == FieldType.values().size ||
                 ((it.keys.size == (FieldType.values().size - 1)) &&
                  !it.keys.contains(FieldType.CID)))
            }.size

            println(nbValidPassports)

        }
    }
}