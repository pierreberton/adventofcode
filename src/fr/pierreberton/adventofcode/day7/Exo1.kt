package fr.pierreberton.adventofcode.day7

import java.io.File
import java.nio.file.Files

class Exo1 {
    companion object {

        @JvmStatic
        fun main(vararg args: String) {

            val colorMap = HashMap<String, ColorNode<String>>()

            Files.readAllLines(File(Exo1::class.java.getResource("./day7.input").path).toPath())
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

            val parentColors = HashSet<String>()
            val listOfColorToRead = ArrayList<String>()
            listOfColorToRead.add("shiny gold")

            while (listOfColorToRead.isNotEmpty()) {
                val currentColor = listOfColorToRead.removeAt(0)
                listOfColorToRead.addAll(colorMap[currentColor]!!.parent.map { it.value })
                parentColors.add(currentColor)
            }

            println(parentColors.size - 1)

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