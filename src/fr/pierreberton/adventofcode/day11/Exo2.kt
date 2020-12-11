package fr.pierreberton.adventofcode.day11

import java.io.File
import java.nio.file.Files

class Exo2 {
    companion object {

        @JvmStatic
        fun main(vararg args: String) {

            var seatsGlobal = mutableListOf<MutableList<Boolean?>>()

            Files.readAllLines(File(Exo2::class.java.getResource("./day11.input").path).toPath())
                .forEach { line ->
                    seatsGlobal.add(mutableListOf())
                    line.forEach {
                        when (it) {
                            'L' -> seatsGlobal.last().add(false)
                            '#' -> seatsGlobal.last().add(true)
                            else -> seatsGlobal.last().add(null)
                        }
                    }
                }

            while (true) {
                val seatsNextState = mutableListOf<MutableList<Boolean?>>()
                for (y in seatsGlobal.indices) {
                    seatsNextState.add(mutableListOf())
                    for (x in seatsGlobal[y].indices) {
                        seatsNextState.last().add(getSeatNextState(seatsGlobal, x, y))
                    }
                }
                if (seatsGlobal.identical(seatsNextState)) {
                    break
                }
                seatsGlobal = seatsNextState.deepCopy()
            }

            println(seatsGlobal.sumOfElementsEqual(true))

        }

        private fun getSeatNextState(seats: List<List<Boolean?>>, x: Int, y: Int): Boolean? {
            return when (seats[y][x]) {
                true -> getVisibleSeats(seats, x, y).filter { it }.size < 5
                false -> getVisibleSeats(seats, x, y).all { !it }
                else -> null
            }
        }

        private fun getVisibleSeats(
            seats: List<List<Boolean?>>, x: Int, y: Int
        ): List<Boolean> {
            val allSeatsAround = getVisiblePoints(
                seats, x, y
            ).filter { seats[it.second][it.first] != null }

            val pointsRight = allSeatsAround.filter { it.first > x && it.second == y }
            val pointsLeft = allSeatsAround.filter { it.first < x && it.second == y }
            val pointsUpper = allSeatsAround.filter { it.first == x && it.second > y }
            val pointsLower = allSeatsAround.filter { it.first == x && it.second < y }
            val pointsRightLower = allSeatsAround.filter { (it.first - it.second == x - y) && it.first > x }
            val pointsLeftUpper = allSeatsAround.filter { (it.first - it.second == x - y) && it.first < x }
            val pointsRightUpper = allSeatsAround.filter { (it.first + it.second == x + y) && it.first > x }
            val pointsLeftLower = allSeatsAround.filter { (it.first + it.second == x + y) && it.first < x }

            val closestSeats = mutableListOf<Pair<Int, Int>>()

            if (pointsRight.isNotEmpty()) {
                closestSeats.add(pointsRight.sortedBy { it.first }.first())
            }
            if (pointsLeft.isNotEmpty()) {
                closestSeats.add(pointsLeft.sortedBy { it.first }.reversed().first())
            }
            if (pointsUpper.isNotEmpty()) {
                closestSeats.add(pointsUpper.sortedBy { it.second }.first())
            }
            if (pointsLower.isNotEmpty()) {
                closestSeats.add(pointsLower.sortedBy { it.second }.reversed().first())
            }
            if (pointsRightLower.isNotEmpty()) {
                closestSeats.add(pointsRightLower.sortedBy { it.first }.first())
            }
            if (pointsLeftUpper.isNotEmpty()) {
                closestSeats.add(
                    pointsLeftUpper.sortedBy { it.first }.reversed().first()
                )
            }
            if (pointsRightUpper.isNotEmpty()) {
                closestSeats.add(pointsRightUpper.sortedBy { it.first }.first())
            }
            if (pointsLeftLower.isNotEmpty()) {
                closestSeats.add(
                    pointsLeftLower.sortedBy { it.first }.reversed().first()
                )
            }

            return closestSeats.map { seats[it.second][it.first]!! }
        }

        private fun getVisiblePoints(
            seats: List<List<Boolean?>>, x: Int, y: Int
        ): List<Pair<Int, Int>> {
            val list = mutableListOf<Pair<Int, Int>>()
            for (i in 1 until seats.size.coerceAtLeast(seats.first().size)) {
                if (x - i >= 0) {
                    list.add(Pair(x - i, y))
                    list.addAll(getPossibleY(seats, x - i, i, y))
                }
                if (x + i < seats.first().size) {
                    list.add(Pair(x + i, y))
                    list.addAll(getPossibleY(seats, x + i, i, y))
                }
                list.addAll(getPossibleY(seats, x, i, y))
            }

            return list
        }

        private fun getPossibleY(
            seats: List<List<Boolean?>>, x: Int, i: Int, y: Int
        ): List<Pair<Int, Int>> {
            val list = mutableListOf<Pair<Int, Int>>()
            if (y - i >= 0) {
                list.add(Pair(x, y - i))
            }
            if (y + i < seats.size) {
                list.add(Pair(x, y + i))
            }
            return list
        }

    }
}