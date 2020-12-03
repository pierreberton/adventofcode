package fr.pierreberton.adventofcode.day2

import java.io.File
import java.nio.file.Files

class Exo1 {
    companion object {
        @JvmStatic
        fun main(vararg args: String) {
            var cptValid = 0
            Files.readAllLines(File(Exo1::class.java.getResource("./day2.input").path).toPath())
                .forEach {
                    val rule = it.split(":")[0].trim()
                    val candidate = it.split(":")[1].trim()
                    val (letter, min, max) = readRule(rule)
                    val count = countLetter(candidate, letter)
                    if (count in min..max) {
                        cptValid++
                    }
                }

            println(cptValid)
        }

        private fun readRule(rawRule: String): Triple<Char, Int, Int> {
            val letter = rawRule.last()
            val min = rawRule.dropLast(2).split("-")[0].toInt()
            val max = rawRule.dropLast(2).split("-")[1].toInt()
            return Triple(letter, min, max)
        }

        private fun countLetter(str: String, letter: Char): Int {
            var cpt = 0
            str.forEach { if (it == letter) cpt++ }
            return cpt
        }
    }
}