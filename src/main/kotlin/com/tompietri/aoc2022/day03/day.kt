package com.tompietri.aoc2022.day03

import com.tompietri.aoc2022.utils.data.Point2D

fun day3FirstSolution(input: List<String>): Int {
    val grid = mutableMapOf<Point2D, Cell>()
    input.forEachIndexed { x, line ->
        line.forEachIndexed { y, char ->
            grid[Point2D(x, y)] = Cell(Point2D(x, y), char)
        }
    }

    var result = 0
    for (x in input.indices) {
        var y = 0;
        while (y < input[x].length) {
            if (grid[Point2D(x, y)]!!.char.isDigit()) {
                val digits = mutableListOf(grid[Point2D(x, y)]!!.char to y)
                if (grid[Point2D(x, y + 1)]?.char?.isDigit() == true) {
                    y++
                    digits.add(grid[Point2D(x, y)]!!.char to y)
                    if (grid[Point2D(x, y + 1)]?.char?.isDigit() == true) {
                        y++
                        digits.add(grid[Point2D(x, y)]!!.char to y)
                    }
                }
                val adjacentCells =
                    getAdjacentCells(Point2D(x, digits.first().second), Point2D(x, digits.last().second))
                if (adjacentCells.any { grid[it]?.char != '.' && grid[it]?.char?.isDigit() == false }) {
                    result += digits.map { it.first }.joinToString("").toInt()
                }
            }
            y++
        }
    }

    return result
}

fun getAdjacentCells(left: Point2D, right: Point2D): List<Point2D> {
    val result = mutableListOf<Point2D>()
    for (x in left.x - 1..right.x + 1) {
        for (y in left.y - 1..right.y + 1) {
            result.add(Point2D(x, y))
        }
    }
    return result
}

fun getAdjacentCells(point: Point2D): List<Point2D> {
    val result = mutableListOf<Point2D>()
    for (x in point.x - 1..point.x + 1) {
        for (y in point.y - 1..point.y + 1) {
            if (x == point.x && y == point.y) continue
            result.add(Point2D(x, y))
        }
    }
    return result
}

fun day3SecondSolution(input: List<String>): Int {
    val grid = mutableMapOf<Point2D, Cell>()
    input.forEachIndexed { x, line ->
        line.forEachIndexed { y, char ->
            grid[Point2D(x, y)] = Cell(Point2D(x, y), char)
        }
    }

    var result = 0
    val gearAdjacentNumbers = mutableMapOf<Point2D, MutableList<Int>>()
    for (x in input.indices) {
        var y = 0;
        while (y < input[x].length) {
            if (grid[Point2D(x, y)]!!.char.isDigit()) {
                val digits = mutableListOf(grid[Point2D(x, y)]!!.char to y)
                if (grid[Point2D(x, y + 1)]?.char?.isDigit() == true) {
                    y++
                    digits.add(grid[Point2D(x, y)]!!.char to y)
                    if (grid[Point2D(x, y + 1)]?.char?.isDigit() == true) {
                        y++
                        digits.add(grid[Point2D(x, y)]!!.char to y)
                    }
                }
                val adjacentCellsWithGear =
                    getAdjacentCells(Point2D(x, digits.first().second), Point2D(x, digits.last().second))
                        .filter { grid[it]?.char == '*' }
                val number = digits.map { it.first }.joinToString("").toInt()
                adjacentCellsWithGear.forEach { gearPosition ->
                    gearAdjacentNumbers.getOrPut(gearPosition) { mutableListOf() }.add(number)
                }
            }
            y++
        }
    }

    gearAdjacentNumbers.forEach { (gearPosition, adjacentNumbers) ->
        if (adjacentNumbers.size == 2) {
            result += adjacentNumbers.first() * adjacentNumbers.last()
        }
    }

    return result
}

private fun parseLeftDigit(
    grid: MutableMap<Point2D, Cell>,
    x: Int,
    y: Int
): Int {
    var digits = listOf(grid[Point2D(x, y)]!!.char)
    if (grid[Point2D(x, y - 2)]?.char?.isDigit() == true) {
        digits = listOf(grid[Point2D(x, y - 2)]!!.char) + digits
        if (grid[Point2D(x, y - 3)]?.char?.isDigit() == true) {
            digits = listOf(grid[Point2D(x, y - 3)]!!.char) + digits
        }
    }
    return digits.joinToString("").toInt()
}

private fun parseRightDigit(
    grid: MutableMap<Point2D, Cell>,
    x: Int,
    y: Int
): Int {
    var digits = listOf(grid[Point2D(x, y)]!!.char)
    if (grid[Point2D(x, y + 2)]?.char?.isDigit() == true) {
        digits = listOf(grid[Point2D(x, y + 2)]!!.char) + digits
        if (grid[Point2D(x, y + 3)]?.char?.isDigit() == true) {
            digits = listOf(grid[Point2D(x, y + 3)]!!.char) + digits
        }
    }
    return digits.joinToString("").toInt()
}

data class Cell(val position: Point2D, val char: Char)