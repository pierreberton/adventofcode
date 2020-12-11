package fr.pierreberton.adventofcode.day11

import java.io.File
import java.nio.file.Files

class Exo1 {
    companion object {

        private var seats = mutableListOf<MutableList<Boolean?>>()

        @JvmStatic
        fun main(vararg args: String) {

            Files.readAllLines(File(Exo1::class.java.getResource("./day11.input").path).toPath())
                .forEach { line ->
                    seats.add(mutableListOf())
                    line.forEach {
                        when (it) {
                            'L' -> seats.last().add(false)
                            '#' -> seats.last().add(true)
                            else -> seats.last().add(null)
                        }
                    }
                }

            while (true) {
                val seatsNextState = mutableListOf<MutableList<Boolean?>>()
                for (y in seats.indices) {
                    seatsNextState.add(mutableListOf())
                    for (x in seats[y].indices) {
                        seatsNextState.last().add(getSeatNextState(x, y))
                    }
                }
                if (seats.identical(seatsNextState)) {
                    break
                }
                seats = seatsNextState.deepCopy()
            }

            println(seats.sumOfElementsEqual(true))

        }

        private fun getSeatNextState(x: Int, y: Int): Boolean? {
            return when (seats[y][x]) {
                true -> getAdjacentSeats(x, y).filter { it != null && it }.size < 4
                false -> getAdjacentSeats(x, y).all { it == null || !it }
                else -> null
            }
        }

        private fun getAdjacentSeats(x: Int, y: Int): List<Boolean?> {
            val points = listOf(
                Pair(x - 1, y - 1), Pair(x, y - 1), Pair(x + 1, y - 1),
                Pair(x - 1, y), Pair(x + 1, y),
                Pair(x - 1, y + 1), Pair(x, y + 1), Pair(x + 1, y + 1)
            )

            val resultList = mutableListOf<Boolean?>()
            for (p in points) {
                if (p.first >= 0 && p.second >= 0 && p.first < seats.first().size &&
                    p.second < seats.size
                ) {
                    resultList.add(seats[p.second][p.first])
                }
            }

            return resultList
        }

    }
}