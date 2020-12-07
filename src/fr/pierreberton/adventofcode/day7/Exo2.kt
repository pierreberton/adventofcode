package fr.pierreberton.adventofcode.day7

import java.io.File
import java.nio.file.Files

class Exo2 {
    companion object {

        @JvmStatic
        fun main(vararg args: String) {

            val colorMap = HashMap<String, ColorNode<String>>()

            Files.readAllLines(File(Exo2::class.java.getResource("./day7.input").path).toPath())
                .forEach { line ->
                    val firstSplit = line.trim().split(" bags contain ")
                    val parentColor = firstSplit[0].trim()

                    if (colorMap[parentColor] == null) {
                        colorMap[parentColor] = ColorNode(parentColor)
                    }

                    val childColors = firstSplit[1].split(",")

                    childColors.forEach {
                        val (color, number) = readNumberAndColorOfBags(it)
                        if (colorMap[color] == null) {
                            colorMap[color] = ColorNode(color)
                        }
                        colorMap[parentColor]?.addChild(colorMap[color]!!, number)
                    }
                }

            println(processNode(colorMap["shiny gold"]!!) - 1)

        }

        private fun processNode(node: ColorNode<String>): Int {
            var total = 1
            if (node.children.isNotEmpty()) {
                node.children.forEach { (child, nb) -> total += (processNode(child) * nb) }
            }
            return total
        }

        private fun readNumberAndColorOfBags(inputStr: String): Pair<String, Int> {
            val trimmedInput = inputStr.trim()
            val number = trimmedInput.takeWhile { it != ' ' }.replace("no", "0").toInt()
            val color = trimmedInput.takeLast(trimmedInput.length - (number.toString().length + 1))
                .split("bag")[0]

            return Pair(color.trim(), number)
        }
    }
}