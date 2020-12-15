package fr.pierreberton.adventofcode.day15

fun main() {
    val input = listOf(7, 14, 0, 17, 11, 1, 2)

    val spokenNumbers = mutableListOf<Int>()
    val memory = mutableMapOf<Int, MutableList<Int>>()

    // Add starting numbers
    input.forEach {
        spokenNumbers.add(it)
        if (memory[it] == null) {
            memory[it] = mutableListOf()
        }
        memory[it]!!.add(spokenNumbers.lastIndex + 1)
    }

    while (spokenNumbers.size < 2020) {
        if (memory[spokenNumbers.last()]!!.size == 1) {
            spokenNumbers.add(0)
            memory[spokenNumbers.last()]!!.add(spokenNumbers.lastIndex + 1)
        } else {
            val numToTell = memory[spokenNumbers.last()]!!.last() -
                            memory[spokenNumbers.last()]!!.dropLast(1).last()
            spokenNumbers.add(numToTell)
            if (memory[spokenNumbers.last()] == null) {
                memory[spokenNumbers.last()] = mutableListOf()
            }
            memory[spokenNumbers.last()]!!.add(spokenNumbers.lastIndex + 1)
        }
    }

    println(spokenNumbers.last())
}