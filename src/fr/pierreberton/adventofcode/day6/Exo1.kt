package fr.pierreberton.adventofcode.day6

import java.io.File
import java.nio.file.Files

class Exo1 {
    companion object {
        @JvmStatic
        fun main(vararg args: String) {

            val groups = ArrayList<MutableMap<Char, Int>>()
            groups.add(HashMap())

            Files.readAllLines(File(Exo1::class.java.getResource("./day6.input").path).toPath())
                    .forEach { line ->
                        if (line.isBlank()) {
                            groups.add(HashMap())
                        } else {
                            line.forEach { groups.last()[it] = groups.last().getOrDefault(it, 0) + 1 }
                        }
                    }

            println(groups.sumOf { it.keys.size })
        }
    }
}