package fr.pierreberton.adventofcode.day7

class ColorNode<T>(var value: T) {

    var parent: MutableList<ColorNode<T>> = mutableListOf()

    var children: MutableMap<ColorNode<T>, Int> = mutableMapOf()

    fun addChild(node: ColorNode<T>, number: Int) {
        children[node] = number
        node.parent.add(this)
    }

}