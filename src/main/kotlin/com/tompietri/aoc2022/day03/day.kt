package com.tompietri.aoc2022.day03

fun day3FirstSolution(input: List<String>): Int {
    return input.map { Rucksack(it.take(it.length / 2), it.takeLast(it.length / 2)) }
        .sumOf { it.score() }
}


fun day3SecondSolution(input: List<String>): Int {

    return input.chunked(3)
        .map { Group(it[0], it[1], it[2]) }
        .sumOf { it.score() }
}

private data class Rucksack(val firstHalf: String, val secondHalf: String) {
    fun score(): Int {
        val secondHalfSet = secondHalf.toSet()
        val char = firstHalf.first { secondHalfSet.contains(it) }
        return if (char.isUpperCase()) char.code - 64 + 26 else char.code - 96
    }
}

private data class Group(val first: String, val second: String, val third: String) {
    fun score(): Int {
        val secondSet = second.toSet()
        val thirdSet = third.toSet()
        val char = first.first { second.contains(it) && third.contains(it) }
        return if (char.isUpperCase()) char.code - 64 + 26 else char.code - 96
    }
}