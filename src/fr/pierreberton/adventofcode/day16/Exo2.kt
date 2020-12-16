package fr.pierreberton.adventofcode.day16

import java.io.File
import java.nio.file.Files

class Exo2 {
    companion object {

        @JvmStatic
        fun main(vararg args: String) {

            var lineNearbyTickets = false
            val myTicket = mutableListOf(
                101, 179, 193, 103, 53, 89, 181, 139, 137, 97, 61, 71, 197, 59, 67, 173, 199, 211,
                191, 131
            )
            val nearbyTickets = mutableListOf<MutableList<Int>>()

            Files.readAllLines(File(Exo2::class.java.getResource("./day16.input").path).toPath())
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

            nearbyTickets.removeIf { ticket ->
                ticket.removeIf {
                    !Field.isRespectingAtLeastOneRule(it)
                }
            }

            val possibleFields = mutableListOf<MutableMap<Field, Int>>()

            for (i in 0 until myTicket.size) {
                possibleFields.add(mutableMapOf())
                nearbyTickets.forEach { ticket ->
                    Field.getPossibleFields(ticket[i]).forEach {
                        possibleFields[i][it] = possibleFields[i][it]?.plus(1) ?: 1
                    }
                }
            }

            possibleFields.forEach { pf ->
                Field.values().forEach { f ->
                    if (pf[f]!! < nearbyTickets.size) {
                        pf.remove(f)
                    }
                }
            }

            val finalMap = mutableListOf<Field>()
            Field.values().forEach { finalMap.add(it) }

            for (i in possibleFields.indices) {
                val pf = possibleFields.first { it.size == 1 }
                val key = pf.keys.first()
                finalMap[possibleFields.indexOf(pf)] = key
                possibleFields.forEach { m ->
                    if (m.isNotEmpty()) {
                        m.remove(key)
                    }
                }
            }

            val date = myTicket[finalMap.indexOf(Field.DEPARTURE_DATE)].toLong()
            val location = myTicket[finalMap.indexOf(Field.DEPARTURE_LOCATION)].toLong()
            val platform = myTicket[finalMap.indexOf(Field.DEPARTURE_PLATFORM)].toLong()
            val station = myTicket[finalMap.indexOf(Field.DEPARTURE_STATION)].toLong()
            val time = myTicket[finalMap.indexOf(Field.DEPARTURE_TIME)].toLong()
            val track = myTicket[finalMap.indexOf(Field.DEPARTURE_TRACK)].toLong()

            println(date * location * platform * station * time * track)
        }

    }
}