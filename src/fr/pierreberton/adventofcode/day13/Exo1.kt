package fr.pierreberton.adventofcode.day13

import java.io.File
import java.nio.file.Files

class Exo1 {
    companion object {

        @JvmStatic
        fun main(vararg args: String) {

            val lines = Files.readAllLines(File(Exo1::class.java.getResource("./day13.input").path).toPath())

            val arrival = lines.first().toLong()

            val busLines = lines.last().split(",").map { if (it == "x") null else it.toLong() }

            var betterBusLine = 0L
            var minDelta = Long.MAX_VALUE

            for (busLine in busLines) {
                if (busLine == null) {
                    continue
                }

                val numberOfPass = arrival / busLine
                val numberOfPassRest = arrival % busLine
                val timeToAdd = if (numberOfPassRest > 0) 1L else 0L
                val nearestTimestamp = (numberOfPass + timeToAdd) * busLine
                val delta = nearestTimestamp - arrival

                if (delta < minDelta) {
                    minDelta = delta
                    betterBusLine = busLine
                }
            }

            println(minDelta * betterBusLine)
        }

    }
}