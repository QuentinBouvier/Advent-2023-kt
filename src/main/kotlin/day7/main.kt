package day7

import kotlin.math.pow

fun main() {
    val example = """32T3K 765
T55J5 684
KK677 28
KTJJT 220
QQQJA 483"""

//    val input = example.split("\n").asSequence()
    val input = commons.inputsFileToLineSequence(7)

    val lines = input
        .map { it.split(" ") }
        .map { it[0] to it[1].toInt() }
        .toList()

    val weightedHands = lines.map { it to (handWeight(it.first) to handValueBase13(it.first)) }

    val p1 = weightedHands
        .sortedWith(compareBy({ it.second.first }, { it.second.second }))
        .mapIndexed { i, it -> it.first.second * (i+1) }
        .sum()

    println("p1: $p1")
}

fun mostPresentChars2(str: String) = str.toCharArray().groupBy { it }.map { it.key to it.value.size }.sortedByDescending { it.second }.take(2).toMap()
fun isXofAKind(str: String, x: Int) = mostPresentChars2(str).any { it.value == x }
fun isFullHouse(str: String) = isXofAKind(str, 3) && isXofAKind(str, 2)
fun isTwoPair(str: String) = mostPresentChars2(str).all { it.value == 2 }
fun handWeight(str: String) = when {
    isXofAKind(str, 5) -> 7
    isXofAKind(str, 4) -> 6
    isFullHouse(str) -> 5
    isXofAKind(str, 3) -> 4
    isTwoPair(str) -> 3
    isXofAKind(str, 2) -> 2
    else -> 1
}
fun handValueBase13(str: String) = str.map { cardToInt(it) }.mapIndexed { i, it -> 13.0.pow(5.0 - i) * it }.sum()
fun cardToInt(c: Char): Int = when (c) {
    in '2'..'9' -> c - '0' - 2
    'T' -> 8
    'J' -> 9
    'Q' -> 10
    'K' -> 11
    'A' -> 12
    else -> error("invalid card")
}