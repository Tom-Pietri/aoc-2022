package com.tompietri.aoc2022.day09

import com.tompietri.aoc2022.utils.data.Point2D
import kotlin.math.abs

fun day9FirstSolution(input: List<String>): Int {
    val moves = input.map { Move.fromString(it) }
    var head = Point2D(0, 0)
    var tail = Point2D(0, 0)
    val visitedSpace = mutableSetOf(tail)

    moves.forEach { move ->
        repeat(move.steps) {
            val previousHead = head
            head = when (move.direction) {
                Direction.DOWN -> head.copy(x = head.x + 1)
                Direction.UP -> head.copy(x = head.x - 1)
                Direction.LEFT -> head.copy(y = head.y - 1)
                Direction.RIGHT -> head.copy(y = head.y + 1)
            }

            val xChange = Math.abs(head.x - tail.x)
            val yChange = Math.abs(head.y - tail.y)
            if (xChange > 1 || yChange > 1) {
                val vector = Point2D(previousHead.x - tail.x, previousHead.y - tail.y)
                tail = Point2D(vector.x + tail.x, vector.y + tail.y)
                visitedSpace.add(previousHead)
            }
        }
    }

    return visitedSpace.count()
}


fun day9SecondSolution(input: List<String>): Int {
    val moves = input.map { Move.fromString(it) }
    val tails = MutableList<Point2D>(10) { Point2D(0, 0) }
    val visitedSpace = mutableSetOf(tails.last())

    moves.forEach { move ->
        repeat(move.steps) {
            val previousPositions = tails.map { it }
            tails[0] = when (move.direction) {
                Direction.DOWN -> tails[0].copy(x = tails[0].x + 1)
                Direction.UP -> tails[0].copy(x = tails[0].x - 1)
                Direction.LEFT -> tails[0].copy(y = tails[0].y - 1)
                Direction.RIGHT -> tails[0].copy(y = tails[0].y + 1)
            }
            val vector = Point2D(previousPositions[0].x - tails[1].x, previousPositions[0].y - tails[1].y)

            for (i in 1 until tails.size) {
                val previousTail = tails[i - 1]
                val tail = tails[i]
                val xChange = Math.abs(previousTail.x - tail.x)
                val yChange = Math.abs(previousTail.y - tail.y)
                if (xChange > 1 || yChange > 1) {
                    tails[i] = Point2D(vector.x + tail.x, vector.y + tail.y)
                }
            }

            tails.print()
            println("=========")

            visitedSpace.add(tails.last())
        }
    }

    return visitedSpace.count()
}

private fun List<Point2D>.print() {
    val allX = this.map { it.x }
    val allY = this.map { it.y }
    for (x in -20 .. 20) {
        for (y in -20 .. 20) {
            val index = this.indexOf(Point2D(x, y))
            if (index != -1) {
                print(index)
            } else {
                print('.')
            }
        }
        println()
    }
}

data class Move(val direction: Direction, val steps: Int) {
    companion object {
        fun fromString(str: String) = Move(
            direction = Direction.fromChar(str.first()),
            steps = str.drop(2).toInt()
        )
    }
}

enum class Direction {
    UP,
    DOWN,
    LEFT,
    RIGHT;

    companion object {
        fun fromChar(char: Char) = when (char) {
            'U' -> UP
            'D' -> DOWN
            'L' -> LEFT
            'R' -> RIGHT
            else -> throw NotImplementedError()
        }
    }
}