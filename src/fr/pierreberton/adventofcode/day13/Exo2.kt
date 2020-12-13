package fr.pierreberton.adventofcode.day13

import java.io.File
import java.math.BigInteger
import java.nio.file.Files

class Exo2 {
    companion object {

        @JvmStatic
        fun main(vararg args: String) {

            val lines = Files.readAllLines(File(Exo2::class.java.getResource("./example.input").path).toPath())

            val busLines = lines.last().split(",").map { if (it == "x") null else it.toLong() }

            val busAndFirstStartFromZero = mutableMapOf<Int, MutableList<BigInteger>>()
            var cpt = 0L
            busLines.forEach {
                if (it != null) {
                    busAndFirstStartFromZero[it.toInt()] = mutableListOf()
                    busAndFirstStartFromZero[it.toInt()]?.add(BigInteger.valueOf(cpt))
                }
                cpt++
            }
            

        }

    }
}