package com.tompietri.aoc2022.day08

import com.tompietri.aoc2022.utils.data.Point2D

fun day8FirstSolution(input: List<String>): Int {
    val grid = input.map { it.toCharArray().map(Char::digitToInt) }
        .mapIndexed { y, values -> values.mapIndexed { x, value -> Point2D(x, y) to Tree(value) } }
        .flatten()
        .toMap()

    for (x in input.indices) {
        var currentPoint = Point2D(x, 0)
        var lastHeight = -1
        while (grid.containsKey(currentPoint)) {
            val currentTree = grid[currentPoint]!!
            if (currentTree.height > lastHeight) {
                currentTree.seen = true
                lastHeight = currentTree.height
            }
            currentPoint = Point2D(x, currentPoint.y + 1)
        }

        currentPoint = Point2D(x, input.size - 1)
        lastHeight = -1
        while (grid.containsKey(currentPoint)) {
            val currentTree = grid[currentPoint]!!
            if (currentTree.height > lastHeight) {
                currentTree.seen = true
                lastHeight = currentTree.height
            }
            currentPoint = Point2D(x, currentPoint.y - 1)
        }
    }

    for (y in input.indices) {
        var currentPoint = Point2D(0, y)
        var lastheight = -1
        while (grid.containsKey(currentPoint)) {
            val currentTree = grid[currentPoint]!!
            if (currentTree.height > lastheight) {
                currentTree.seen = true
                lastheight = currentTree.height
            }
            currentPoint = Point2D(currentPoint.x + 1, y)
        }

        currentPoint = Point2D(input.size - 1, y)
        lastheight = -1
        while (grid.containsKey(currentPoint)) {
            val currentTree = grid[currentPoint]!!
            if (currentTree.height > lastheight) {
                currentTree.seen = true
                lastheight = currentTree.height
            }
            currentPoint = Point2D(currentPoint.x - 1, y)
        }
    }

    return grid.values.count { it.seen }
}


fun day8SecondSolution(input: List<String>): Int {
    val grid = input.map { it.toCharArray().map(Char::digitToInt) }
        .mapIndexed { y, values -> values.mapIndexed { x, value -> Point2D(x, y) to Tree(value) } }
        .flatten()
        .toMap()

    return grid.keys.maxOf { computeScenicScore(it, grid) }
}

private fun computeScenicScore(position: Point2D, grid: Map<Point2D, Tree>): Int {
    val tree = grid[position]!!

    var seenDown = 0
    while (true) {
        val treeToCheck = grid[position.copy(y = position.y + seenDown + 1)] ?: break
        seenDown++
        if (treeToCheck.height >= tree.height) {
            break
        }
    }

    var seenUp = 0
    while (true) {
        val treeToCheck = grid[position.copy(y = position.y - seenUp - 1)] ?: break
        seenUp++
        if (treeToCheck.height >= tree.height) {
            break
        }
    }

    var seenRight = 0
    while (true) {
        val treeToCheck = grid[position.copy(x = position.x + seenRight + 1)] ?: break
        seenRight++
        if (treeToCheck.height >= tree.height) {
            break
        }
    }

    var seenLeft = 0
    while (true) {
        val treeToCheck = grid[position.copy(x = position.x - seenLeft - 1)] ?: break
        seenLeft++
        if (treeToCheck.height >= tree.height) {
            break
        }
    }


    return seenUp * seenDown * seenLeft * seenRight
}

data class Tree(val height: Int, var seen: Boolean = false)
