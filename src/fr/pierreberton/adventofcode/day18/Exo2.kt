package fr.pierreberton.adventofcode.day18

import java.io.File
import java.nio.file.Files


class Exo2 {
    companion object {

        @JvmStatic
        fun main(vararg args: String) {

            var sum = 0L

            Files.readAllLines(File(Exo2::class.java.getResource("./day18.input").path).toPath())
                .forEach { line ->
                    var calc = line

                    while (calc.contains("""[()]""".toRegex())) {
                        val start = calc.indexOfLast { it == '(' }
                        val end = calc.takeLastWhile { it != '(' }
                                      .indexOfFirst { it == ')' } + start + 1
                        val res = simpleCalc(calc.substring(start + 1, end))
                        val newCalc = calc.substring(0, start) + res + calc.substring(end + 1)
                        calc = newCalc
                    }

                    sum += simpleCalc(calc).toLong()
                }

            println(sum)
        }

        fun simpleCalc(calc: String): String {
            val operands = calc.split("""[*+]""".toRegex()).map { it.trim() }.toMutableList()
            val operators = mutableListOf<Char>()
            calc.forEach { if (it == '*' || it == '+') operators.add(it) }

            while (operators.contains('+')) {
                val idxOpe = operators.lastIndexOf('+')
                val operand1 = operands[idxOpe]
                val operand2 = operands[idxOpe + 1]
                val res = operand1.toLong() + operand2.toLong()
                operands.removeAt(idxOpe)
                operands.removeAt(idxOpe)
                operands.add(idxOpe, res.toString())
                operators.removeAt(idxOpe)
            }

            while (operands.size > 1) {
                val res = when (operators[0]) {
                    '*' -> operands[0].toLong() * operands[1].toLong()
                    else -> throw Exception()
                }
                operators.removeAt(0)
                operands.removeAt(0)
                operands.removeAt(0)
                operands.add(0, res.toString())
            }

            return operands[0]
        }

    }

}