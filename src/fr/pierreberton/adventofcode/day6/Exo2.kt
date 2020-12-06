package fr.pierreberton.adventofcode.day6

import java.io.File
import java.nio.file.Files
import java.util.concurrent.atomic.AtomicInteger

class Exo2 {
    companion object {
        @JvmStatic
        fun main(vararg args: String) {

            val groups = ArrayList<Pair<MutableMap<Char, Int>, AtomicInteger>>()
            groups.add(Pair(HashMap(), AtomicInteger(0)))

            Files.readAllLines(File(Exo2::class.java.getResource("./day6.input").path).toPath())
                    .forEach { line ->
                        if (line.isBlank()) {
                            groups.add(Pair(HashMap(), AtomicInteger(0)))
                        } else {
                            line.forEach {
                                groups.last().first[it] = groups.last().first.getOrDefault(it, 0) + 1
                            }
                            groups.last().second.incrementAndGet()
                        }
                    }

            println(groups.sumOf { grp -> grp.first.values.sumOf { it / grp.second.get() } })
        }
    }
}