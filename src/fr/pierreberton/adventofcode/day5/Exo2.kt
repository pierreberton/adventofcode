package fr.pierreberton.adventofcode.day5

import java.io.File
import java.nio.file.Files
import kotlin.math.max

class Exo2 {
    companion object {
        @JvmStatic
        fun main(vararg args: String) {

            val seatIds = ArrayList<Int>()

            Files.readAllLines(File(Exo2::class.java.getResource("./day5.input").path).toPath())
                    .forEach { line ->
                        var rows = Pair(0, 127)
                        var cols = Pair(0, 7)

                        line.take(7).forEach { rows = getHalfOf(it, rows) }
                        line.takeLast(3).forEach { cols = getHalfOf(it, cols) }

                        val row = max(rows.first, rows.second)
                        val col = max(cols.first, cols.second)

                        seatIds.add(row * 8 + col)
                    }

            seatIds.sort()

            var lastSeatTested = seatIds[0] - 1
            seatIds.forEach {
                if (it in seatIds[0]..seatIds[seatIds.size - 1]) {
                    if (lastSeatTested + 1 != it) {
                        println(lastSeatTested + 1)
                        return
                    }
                    lastSeatTested = it
                }
            }
        }

        private fun getHalfOf(letter: Char, num: Pair<Int, Int>): Pair<Int, Int> {
            return when (letter) {
                'B', 'R' -> Pair(((num.second - num.first) / 2) + num.first + 1, num.second)
                'F', 'L' -> Pair(num.first, ((num.second - num.first) / 2) + num.first)
                else -> throw Exception()
            }
        }
    }
}