package fr.pierreberton.adventofcode.day15

fun main() {
    val input = listOf(7, 14, 0, 17, 11, 1, 2)

    val memory = mutableMapOf<Int, MutableList<Int>>()
    var cpt = 1
    var lastNumberSpoken = 0

    // Add starting numbers
    input.forEach {
        lastNumberSpoken = it
        if (memory[it] == null) {
            memory[it] = mutableListOf()
        }
        memory[it]!!.add(cpt++)
    }

    while (cpt <= 30000000) {
        if (memory[lastNumberSpoken]!!.size == 1) {
            lastNumberSpoken = 0
            memory[lastNumberSpoken]!!.add(cpt++)
            memory[lastNumberSpoken] = memory[lastNumberSpoken]!!.takeLast(2).toMutableList()
        } else {
            val numToTell = memory[lastNumberSpoken]!!.last() -
                            memory[lastNumberSpoken]!!.first()
            lastNumberSpoken = numToTell
            if (memory[lastNumberSpoken] == null) {
                memory[lastNumberSpoken] = mutableListOf()
            }
            memory[lastNumberSpoken]!!.add(cpt++)
            memory[lastNumberSpoken] = memory[lastNumberSpoken]!!.takeLast(2).toMutableList()
        }
    }

    println(lastNumberSpoken)
}