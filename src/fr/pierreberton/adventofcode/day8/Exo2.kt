package fr.pierreberton.adventofcode.day8

import java.io.File
import java.nio.file.Files

class Exo2 {
    companion object {

        @JvmStatic
        fun main(vararg args: String) {

            val commands = ArrayList<Pair<String, Int>>()

            Files.readAllLines(File(Exo2::class.java.getResource("./day8.input").path).toPath())
                .forEach { line ->
                    val lineParts = line.split(" ")
                    commands.add(Pair(lineParts[0].trim(), lineParts[1].trim().toInt()))
                }


            val engineFirstTry = engine(commands)
            var offset = 0
            var accumulator = engineFirstTry.first
            val originalVisitedIdx = engineFirstTry.second
            var visitedIdx = originalVisitedIdx.toMutableList()

            while (visitedIdx.last() != commands.lastIndex) {
                val tmpCmdArray = commands.toMutableList()
                visitedIdx = originalVisitedIdx.toMutableList()
                for (idx in (visitedIdx.lastIndex - offset) downTo 0) {
                    val currCmdIdx = visitedIdx[idx]
                    if (tmpCmdArray[currCmdIdx].first == "jmp") {
                        tmpCmdArray[currCmdIdx] = Pair("nop", tmpCmdArray[currCmdIdx].second)
                        break
                    } else if (tmpCmdArray[currCmdIdx].first == "nop") {
                        tmpCmdArray[currCmdIdx] = Pair("jmp", tmpCmdArray[currCmdIdx].second)
                        break
                    }
                }
                val (acc, vIdx) = engine(tmpCmdArray)
                accumulator = acc
                visitedIdx = vIdx
                offset++
            }

            println("Last visited index before loop : " + visitedIdx.last())
            println("Acc value : $accumulator")
        }

        private fun engine(
            commands: MutableList<Pair<String, Int>>
        ): Pair<Int, ArrayList<Int>> {
            var accumulator = 0
            var currentCmdIdx = 0
            val visitedIdx = ArrayList<Int>()
            while (!visitedIdx.contains(currentCmdIdx) &&
                   currentCmdIdx != commands.size
            ) {
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
            return Pair(accumulator, visitedIdx)
        }

    }
}