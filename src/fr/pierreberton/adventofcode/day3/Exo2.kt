package fr.pierreberton.adventofcode.day3

import java.io.File
import java.nio.file.Files

class Exo2 {
    companion object {
        @JvmStatic
        fun main(vararg args: String) {

            val matrix = ArrayList<ArrayList<Char>>()

            Files.readAllLines(File(Exo2::class.java.getResource("./day3.input").path).toPath())
                .forEach { line ->
                    matrix.add(ArrayList())
                    line.forEach { matrix.last().add(it) }
                }

            val nbTrees = ArrayList<Int>()
            val movements = arrayListOf(Pair(1, 1), Pair(3, 1), Pair(5, 1), Pair(7, 1), Pair(1, 2))

            for (mvt in movements) {
                var point = Pair(0, 0)
                var cpt = 0
                for (x in mvt.second until matrix.size step mvt.second) {
                    point = Pair(x, (point.second + mvt.first) % matrix[x].size)
                    if (matrix[point.first][point.second] == '#') {
                        cpt++
                    }
                }
                nbTrees.add(cpt)
            }

            println(nbTrees.reduce { a: Int, b: Int -> a * b })
        }
    }
}