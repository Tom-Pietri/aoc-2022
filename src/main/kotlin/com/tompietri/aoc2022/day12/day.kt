package com.tompietri.aoc2022.day12

import com.tompietri.aoc2022.utils.data.Point2D

fun day12FirstSolution(input: List<String>): Int {
    val heightMap = HeightMap.fromInput(input)

    val shortestPathMap = buildDistanceMap(heightMap, heightMap.start)

    return shortestPathMap[heightMap.end]!!
}

private fun buildDistanceMap(heightMap: HeightMap, startPoint: Point2D): Map<Point2D, Int> {
    data class PointToCheck(val target: Point2D, val source: Point2D, val pathLength: Int)

    val shortestPathMap = mutableMapOf<Point2D, Int>()

    shortestPathMap[startPoint] = 0
    val pointsToCheck = mutableListOf<PointToCheck>()
    pointsToCheck.addAll(startPoint.generateAdjacent().map { PointToCheck(it, startPoint, 0) })

    while (pointsToCheck.isNotEmpty()) {
        val nextPoint = pointsToCheck.removeFirst()
        if (heightMap.grid[nextPoint.source]!! + 1 >= (heightMap.grid[nextPoint.target] ?: Int.MAX_VALUE)) {
            val existingValue = shortestPathMap[nextPoint.target]
            val pathLength = nextPoint.pathLength + 1
            if (existingValue == null || pathLength < existingValue) {
                shortestPathMap[nextPoint.target] = pathLength
                val newPoints =
                    nextPoint.target.generateAdjacent().map { PointToCheck(it, nextPoint.target, pathLength) }
                pointsToCheck.addAll(newPoints)
            }
        }
    }

    return shortestPathMap
}


fun day12SecondSolution(input: List<String>): Int {
    val heightMap = HeightMap.fromInput(input)
    return heightMap.grid.filter { it.value == 1 }.map {
        val shortestPathMap = buildDistanceMap(heightMap, it.key)

        shortestPathMap[heightMap.end] ?: Int.MAX_VALUE
    }.min()
}

private class HeightMap(val start: Point2D, val end: Point2D, val grid: Map<Point2D, Int>) {

    companion object {
        fun fromInput(input: List<String>): HeightMap {
            var start = Point2D(0, 0)
            var end = Point2D(0, 0)
            val grid = input.flatMapIndexed { y, s ->
                s.mapIndexed { x, c ->
                    val position = Point2D(x, y)
                    if (c == 'S') {
                        start = position
                        position to 1
                    } else if (c == 'E') {
                        end = position
                        position to 26
                    } else {
                        position to (c.code - 'a'.code + 1)
                    }
                }
            }.toMap()

            return HeightMap(start, end, grid)
        }
    }
}