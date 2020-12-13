package fr.pierreberton.adventofcode.day12

import java.io.File
import java.nio.file.Files
import kotlin.math.abs

class Exo2 {
    companion object {

        @JvmStatic
        fun main(vararg args: String) {

            val instructions = mutableListOf<Pair<Action, Int>>()

            Files.readAllLines(File(Exo2::class.java.getResource("./day12.input").path).toPath())
                    .forEach {
                        instructions.add(Pair(Action.valueOf(it.take(1)), it.drop(1).toInt()))
                    }

            // Ship position where (0,0) is the starting point
            // x is the West-East axis
            // y is the North-South axis
            var position = Point(0, 0)
            var waypoint = Point(10, 1)

            for ((act, num) in instructions) {
                when (act) {
                    Action.N -> waypoint.y += num
                    Action.S -> waypoint.y -= num
                    Action.E -> waypoint.x += num
                    Action.W -> waypoint.x -= num
                    Action.R, Action.L -> waypoint = performAngleOperation(act, num, waypoint)
                    Action.F -> {
                        position = goToWaypoint(num, position, waypoint)
                    }
                }
            }

            println("Current position : (${position.x},${position.y})")
            println("Manhattan distance : " + (abs(position.x) + abs(position.y)))

        }

        private fun goToWaypoint(times: Int, start: Point, target: Point): Point {
            return Point(start.x + (times * target.x), start.y + (times * target.y))
        }

        private fun performAngleOperation(action: Action, number: Int, target: Point): Point {
            var angle = number % 360

            angle = when (action) {
                Action.R -> 360 - angle
                Action.L -> angle
                else -> throw Exception("Not an angle modification")
            }

            val rotationsToPerform = angle / 90
            var relativePoint = Point(target.x, target.y)

            for (i in 1..rotationsToPerform) {
                relativePoint = performPositiveRotation90Degrees(relativePoint)
            }

            return relativePoint
        }

        private fun performPositiveRotation90Degrees(point: Point): Point {
            return Point(point.y * -1, point.x)
        }

    }

    enum class Action {
        N, S, E, W, R, L, F;
    }

    class Point(var x: Int, var y: Int)
}