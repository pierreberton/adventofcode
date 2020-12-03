package fr.pierreberton.adventofcode.day2

import java.io.File
import java.nio.file.Files

class Exo2 {
    companion object {
        @JvmStatic
        fun main(vararg args: String) {
            var cptValid = 0
            Files.readAllLines(File(Exo2::class.java.getResource("./day2.input").path).toPath())
                .forEach {
                    val rule = it.split(":")[0].trim()
                    val candidate = it.split(":")[1].trim()
                    if (checkPosition(candidate, readRule(rule))) {
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

        private fun checkPosition(str: String, rules: Triple<Char, Int, Int>): Boolean {
            return (str[rules.second - 1] == rules.first) xor
                    (str[rules.third - 1] == rules.first)
        }
    }
}