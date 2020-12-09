package fr.pierreberton.adventofcode.day9

import java.io.File
import java.nio.file.Files

class Exo1 {
    companion object {

        @JvmStatic
        fun main(vararg args: String) {

            val numbers = mutableListOf<Long>()

            Files.readAllLines(File(Exo1::class.java.getResource("./day9.input").path).toPath())
                .forEach { numbers.add(it.toLong()) }

            var indexToTest = 25

            while (indexToTest < numbers.size) {
                val preamble = numbers.subList(indexToTest - 25, indexToTest)
                val possibleValues = possibleNumbers(preamble)

                if (numbers[indexToTest] !in possibleValues) {
                    break
                }
                indexToTest++
            }

            if (indexToTest < numbers.size) {
                println(numbers[indexToTest])
            }

        }

        fun possibleNumbers(list: MutableList<Long>): MutableSet<Long> {
            val list2 = list.toMutableList()
            val outputSet = mutableSetOf<Long>()

            for (l1 in list) {
                for (l2 in list2) {
                    outputSet.add(l1 + l2)
                }
            }

            return outputSet
        }

    }
}