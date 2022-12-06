package com.tompietri.aoc2022.day02

fun day2FirstSolution(input: List<String>): Int = input.map { it.split(" ") }
    .map { Round(it[0].first(), it[1].first()) }
    .sumOf { it.computeScore() }
fun day2SecondSolution(input: List<String>): Int = input.map { it.split(" ") }
    .map { Round(it[0].first(), it[1].first()) }
    .map { it.computeRoundToPlay() }
    .sumOf { it.computeScore() }

private data class Round(val opponent: Char, val me: Char) {
    fun computeScore(): Int {
        val shapeScore = when(me) {
            'X' -> 1
            'Y' -> 2
            'Z' -> 3
            else -> throw IllegalArgumentException()
        }

        val roundScore = when(me) {
            'X' -> if(opponent == 'A') 3 else if(opponent == 'B') 0 else 6
            'Y' -> if(opponent == 'A') 6 else if(opponent == 'B') 3 else 0
            'Z' -> if(opponent == 'A') 0 else if(opponent == 'B') 6 else 3
            else -> throw IllegalArgumentException()
        }

        return roundScore + shapeScore
    }

    // X means you need to lose, Y means you need to end the round in a draw, and Z means you need to win.
    fun computeRoundToPlay(): Round {
        val myAction = when (me) {
            'X' -> if(opponent == 'A') 'Z' else if(opponent == 'B') 'X' else 'Y'
            'Y' -> if(opponent == 'A') 'X' else if(opponent == 'B') 'Y' else 'Z'
            'Z' -> if(opponent == 'A') 'Y' else if(opponent == 'B') 'Z' else 'X'
            else -> throw IllegalArgumentException()
        }

        return Round(opponent, myAction)
    }
}