package com.tompietri.aoc2022.day02

import kotlin.math.max

fun day2FirstSolution(input: List<String>): Int {
    val games = mapGames(input)

    return games.filter { game ->
        game.draws.all { draw ->
            draw.nbRed <= 12 && draw.nbGreen <= 13 && draw.nbBlue <= 14
        }
    }.sumOf { it.index }
}

fun day2SecondSolution(input: List<String>): Int {
    return mapGames(input)
        .map { game ->
            game.draws.reduce { acc, draw ->
                Draw(max(acc.nbRed, draw.nbRed), max(acc.nbGreen, draw.nbGreen), max(acc.nbBlue, draw.nbBlue))
            }
        }.sumOf { draw -> draw.nbRed * draw.nbGreen * draw.nbBlue }
}

private fun mapGames(input: List<String>): List<Game> {
    val regex = Regex("(\\d+) (blue|red|green)")
    val games = input.mapIndexed { index, line ->
        val drawStrings = line.substringAfter(':')
            .split(";")
        Game(index + 1, drawStrings.map { draw ->
            val nbEach = draw
                .split(',')
                .map { regex.find(it)!! }
                .map { it.groupValues[1].toInt() to it.groupValues[2] }
            Draw(
                nbEach.find { it.second == "red" }?.first ?: 0,
                nbEach.find { it.second == "green" }?.first ?: 0,
                nbEach.find { it.second == "blue" }?.first ?: 0
            )
        }
        )
    }
    return games
}

public data class Game(val index: Int, val draws: List<Draw>);
data class Draw(val nbRed: Int, val nbGreen: Int, val nbBlue: Int);