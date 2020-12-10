package fr.pierreberton.adventofcode.day10

import java.io.File
import java.nio.file.Files

class Exo1 {
    companion object {

        @JvmStatic
        fun main(vararg args: String) {

            val adapters = mutableListOf<Int>()

            Files.readAllLines(File(Exo1::class.java.getResource("./day10.input").path).toPath())
                .forEach { adapters.add(it.toInt()) }

            adapters.sort()

            var cptDiff1 = 0
            var cptDiff2 = 0
            var cptDiff3 = 0
            var lastAdapter = 0

            adapters.forEach {
                when (it - lastAdapter) {
                    1 -> cptDiff1++
                    2 -> cptDiff2++
                    3 -> cptDiff3++
                }
                lastAdapter = it
            }

            // Last one
            cptDiff3++

            println(cptDiff1 * cptDiff3)

        }

    }
}