package fr.pierreberton.adventofcode.day16

import java.io.File
import java.nio.file.Files

class Exo1 {
    companion object {

        @JvmStatic
        fun main(vararg args: String) {

            var lineNearbyTickets = false
            val nearbyTickets = mutableListOf<MutableList<Int>>()

            Files.readAllLines(File(Exo1::class.java.getResource("./day16.input").path).toPath())
                .forEach { line ->
                    if (line.isNotBlank()) {
                        if (line.startsWith("nearby tickets")) {
                            lineNearbyTickets = true
                        } else if (lineNearbyTickets) {
                            nearbyTickets.add(mutableListOf())
                            line.split(",").forEach { nearbyTickets.last().add(it.toInt()) }
                        }
                    }
                }

            var errorRate = 0
            nearbyTickets.forEach { ticket ->
                ticket.forEach {
                    if (!Field.isRespectingAtLeastOneRule(it)) {
                        errorRate += it
                    }
                }
            }

            println(errorRate)
        }

    }
}