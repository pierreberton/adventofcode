package fr.pierreberton.adventofcode.day11

fun <T> List<List<T>>.identical(other: List<List<T>>): Boolean {
    if (size != other.size) {
        return false
    }
    for (i in indices) {
        if (get(i).size != other[i].size) {
            return false
        }
        for (j in get(i).indices) {
            if (get(i)[j] != other[i][j]) {
                return false
            }
        }
    }

    return true
}

fun <T> List<List<T>>.sumOfElementsEqual(elem: T): Int {
    var cpt = 0
    for (i in indices) {
        for (j in get(i).indices) {
            if (get(i)[j] == elem) {
                cpt++
            }
        }
    }

    return cpt
}

fun <T> MutableList<MutableList<T>>.deepCopy(): MutableList<MutableList<T>> {
    val other = mutableListOf<MutableList<T>>()

    for (i in indices) {
        other.add(mutableListOf())
        for (j in get(i).indices) {
            other.last().add(get(i)[j])
        }
    }

    return other
}