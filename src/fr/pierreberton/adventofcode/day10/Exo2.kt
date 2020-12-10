package fr.pierreberton.adventofcode.day10

import java.io.File
import java.nio.file.Files

class Exo2 {
    companion object {

        @JvmStatic
        fun main(vararg args: String) {

            val adapters = mutableListOf<Int>()

            Files.readAllLines(File(Exo2::class.java.getResource("./day10.input").path).toPath())
                .forEach { adapters.add(it.toInt()) }

            adapters.sort()

            var offset = 0
            val divider = 2
            val step = adapters.size / divider
            val subLists = mutableListOf<MutableList<Int>>()
            for (cpt in 0 until divider) {
                subLists.add(adapters.subList(offset, step + offset))
                offset += step
            }
            if (offset < adapters.size) {
                subLists.add(adapters.subList(offset, adapters.size))
            }

            var combinations = 1L
            var lastValue = 0
            subLists.forEach {
                combinations *= findNextSteps(it, lastValue)
                lastValue = it.last()
            }
            println(combinations)

        }

        private fun findNextSteps(
            adapters: MutableList<Int>,
            currentValue: Int
        ): Long {
            val currentWays = mutableListOf<Int>()

            if (adapters.contains(currentValue + 1)) currentWays.add(currentValue + 1)
            if (adapters.contains(currentValue + 2)) currentWays.add(currentValue + 2)
            if (adapters.contains(currentValue + 3)) currentWays.add(currentValue + 3)

            var sumOfWays = 0L
            for (way in currentWays) {
                sumOfWays += findNextSteps(adapters, way)
            }

            return if (sumOfWays == 0L) 1L else sumOfWays
        }

    }
}