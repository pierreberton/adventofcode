package fr.pierreberton.adventofcode.day3

import java.io.File
import java.nio.file.Files

class Exo1 {
    companion object {
        @JvmStatic
        fun main(vararg args: String) {

            val matrix = ArrayList<ArrayList<Char>>()

            Files.readAllLines(File(Exo1::class.java.getResource("./day3.input").path).toPath())
                .forEach { line ->
                    matrix.add(ArrayList())
                    line.forEach { matrix.last().add(it) }
                }

            var point = Pair(0, 0)
            var nbTrees = 0

            for (x in 1 until matrix.size) {
                point = Pair(x, (point.second + 3) % matrix[x].size)
                if (matrix[point.first][point.second] == '#') {
                    nbTrees++
                }
            }

            println(nbTrees)
        }
    }
}