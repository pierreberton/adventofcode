package fr.pierreberton.adventofcode.day8

import java.io.File
import java.nio.file.Files

class Exo1 {
    companion object {

        @JvmStatic
        fun main(vararg args: String) {

            val commands = ArrayList<Pair<String, Int>>()
            val visitedIdx = ArrayList<Int>()
            var accumulator = 0
            var currentCmdIdx = 0

            Files.readAllLines(File(Exo1::class.java.getResource("./day8.input").path).toPath())
                .forEach { line ->
                    val lineParts = line.split(" ")
                    commands.add(Pair(lineParts[0].trim(), lineParts[1].trim().toInt()))
                }

            while (!visitedIdx.contains(currentCmdIdx)) {
                val currentCmd = commands[currentCmdIdx]
                visitedIdx.add(currentCmdIdx)
                when (currentCmd.first) {
                    "acc" -> {
                        accumulator += currentCmd.second
                        currentCmdIdx++
                    }
                    "jmp" -> currentCmdIdx += currentCmd.second
                    "nop" -> currentCmdIdx++
                    else -> throw Exception()
                }
            }

            println("Last visited index : " + visitedIdx[visitedIdx.lastIndex - 1])
            println("Acc value : $accumulator")
        }

    }
}