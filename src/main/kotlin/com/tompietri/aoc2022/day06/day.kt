package com.tompietri.aoc2022.day06

import java.math.BigInteger

fun day6FirstSolution(input: List<String>): Int {
    val timeValues = extractLineValues(input[0])
    val distanceValues = extractLineValues(input[1])
    var races = timeValues.zip(distanceValues)
        .map { Race(it.first, it.second.toBigInteger()) }
    return races.map { it.getNbPossibleWaysToWin() }
        .reduce { acc, other -> acc * other }
}

private fun extractLineValues(line: String) = line
    .split(":")[1]
    .split(" ")
    .filter { it.isNotBlank() }
    .map { it.toInt() }

fun day6SecondSolution(input: List<String>): Int {
    val timeValues = input[0].split(":")[1]
    val distanceValues = input[1].split(":")[1]

    val time = timeValues.filter { it != ' ' }.toInt()
    val distance = distanceValues.filter { it != ' ' }.toBigInteger()

    val race = Race(time, distance)
    return race.getNbPossibleWaysToWin()
}

data class Race(val time: Int, val distance: BigInteger) {
    fun getNbPossibleWaysToWin(): Int {
        var nbWins = 0
        for (timeToPress in 1 until time) {
            val remainingTime = time - timeToPress
            if (remainingTime.toBigInteger() * timeToPress.toBigInteger() > distance) {
                nbWins++
            }

        }

        return nbWins
    }
}