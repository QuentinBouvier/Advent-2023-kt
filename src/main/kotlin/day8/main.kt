package day8

import commons.inputsFileToLineSequence

fun main() {
    val input = inputsFileToLineSequence(8).toList()

//    val input = """LLR
//
//AAA = (BBB, BBB)
//BBB = (AAA, ZZZ)
//ZZZ = (ZZZ, ZZZ)""".splitToSequence("\n")
//    val input = """RL
//
//AAA = (BBB, CCC)
//BBB = (DDD, EEE)
//CCC = (ZZZ, GGG)
//DDD = (DDD, DDD)
//EEE = (EEE, EEE)
//GGG = (GGG, GGG)
//ZZZ = (ZZZ, ZZZ)""".splitToSequence("\n")

    val inputList = input.toList()

    part1(inputList)
}

private fun part1(inputList: List<String>) {
    val path = inputList.first()
    val nodes = inputList.drop(2).map { Node.fromInput(it) }

    var currentNode = nodes.first() { it.name == "AAA" }
    var counter = 0

    while (currentNode.name != "ZZZ") {
        val dir = path[counter % path.length]
        val nextNode =
            nodes.find { it.name == currentNode.getNext(dir) } ?: throw IllegalArgumentException("Node not found")
        currentNode = nextNode
        counter++
    }

    println(counter)
}

private data class Node(val name: String, val left: String, val right: String) {
    fun getNext(dir: Char): String {
        return when (dir) {
            'L' -> left
            'R' -> right
            else -> throw IllegalArgumentException("Invalid direction")
        }
    }

    companion object {
        fun fromInput(node: String): Node {
            val splitEquals = node.split(" = ")
            val name = splitEquals[0]
            val left = splitEquals[1].trim('(', ')').split(", ")[0]
            val right = splitEquals[1].trim('(', ')').split(", ")[1]

            return Node(name, left, right)
        }
    }
}