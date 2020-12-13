package fr.pierreberton.adventofcode.day12

import java.io.File
import java.nio.file.Files
import kotlin.math.abs

class Exo1 {
    companion object {

        @JvmStatic
        fun main(vararg args: String) {

            val instructions = mutableListOf<Pair<Action, Int>>()

            Files.readAllLines(File(Exo1::class.java.getResource("./day12.input").path).toPath())
                    .forEach {
                        instructions.add(Pair(Action.valueOf(it.take(1)), it.drop(1).toInt()))
                    }

            // Ship facing angle in degrees
            var shipFacing = 90

            // Ship position where (0,0) is the starting point
            // x is the West-East axis
            // y is the North-South axis
            val position = Point(0, 0)

            for ((act, num) in instructions) {
                when (act) {
                    Action.N -> position.y += num
                    Action.S -> position.y -= num
                    Action.E -> position.x += num
                    Action.W -> position.x -= num
                    Action.R, Action.L -> shipFacing = performAngleOperation(act, num, shipFacing)
                    Action.F -> when (shipFacing) {
                        0 -> position.y += num
                        90 -> position.x += num
                        180 -> position.y -= num
                        270 -> position.x -= num
                        else -> throw Exception("Wrong ship facing angle")
                    }
                }
            }

            println("Current position : (${position.x},${position.y})")
            println("Manhattan distance : " + (abs(position.x) + abs(position.y)))

        }

        private fun performAngleOperation(action: Action, number: Int, originalAngle: Int): Int {
            var newAngle = when (action) {
                Action.R -> originalAngle + number
                Action.L -> originalAngle - number
                else -> throw Exception("Not an angle modification")
            }

            newAngle %= 360

            if (newAngle < 0) {
                newAngle += 360
            }

            return newAngle
        }

    }

    enum class Action {
        N, S, E, W, R, L, F;
    }

    class Point(var x: Int, var y: Int)
}