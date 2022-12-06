package com.tompietri.aoc2022.day06

fun day6FirstSolution(input: String): Int = i(input, 4)

fun day6SecondSolution(input: String): Int = i(input, 14)

private fun i(input: String, markerSize: Int): Int {
    val index = input.windowed(markerSize)
        .map { s -> s.toCharArray().toSet() }
        .mapIndexed { index, chars -> if (chars.count() == markerSize) index else -1 }
        .first { it != -1 }
    return index + markerSize
}
