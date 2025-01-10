package y2023.day9

import commons.inputsFileToLineSequence

fun main() {
    val input = inputsFileToLineSequence(2023, 9).toList()

//    val input = """0 3 6 9 12 15
//1 3 6 10 15 21
//10 13 16 21 30 45""".split("\n")

    part1(input)
}

fun part1(input: List<String>) {
    val p1 = input.fold(0) { result, line ->
        val matrix: MutableList<List<Int>> = mutableListOf()
        var l = line.split(" ").map { it.toInt() }
        while (!l.all { it == 0 }) {
            matrix.add(l)
            l = addWithNext(l)
        }
        result + matrix.fold(0) { t, it -> t + it.last() }
    }
    println(p1)
}

fun addWithNext(l: List<Int>) = l.zipWithNext().map { it.second - it.first }
