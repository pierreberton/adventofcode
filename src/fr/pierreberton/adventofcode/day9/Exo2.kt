package fr.pierreberton.adventofcode.day9

import java.io.File
import java.nio.file.Files

class Exo2 {
    companion object {

        @JvmStatic
        fun main(vararg args: String) {

            val numbers = mutableListOf<Long>()

            Files.readAllLines(File(Exo2::class.java.getResource("./day9.input").path).toPath())
                .forEach { numbers.add(it.toLong()) }

            val numberToFind = 138879426L
            var numbersPartial = numbers.toMutableList()

            while (numbersPartial.isNotEmpty()) {
                var sum = 0L
                var currentIndex = 0
                while (sum < numberToFind) {
                    sum += numbersPartial[currentIndex++]
                }

                if (sum == numberToFind) {
                    val min = numbersPartial.take(currentIndex).minOrNull() ?: Long.MAX_VALUE
                    val max = numbersPartial.take(currentIndex).maxOrNull() ?: Long.MIN_VALUE
                    if (min < max) {
                        println(min + max)
                    }
                }
                numbersPartial = numbersPartial.drop(1).toMutableList()
            }

        }

    }
}