package com.tompietri.aoc2022.utils.data

data class Point2D(val x: Int, val y: Int) : Comparable<Point2D> {
    override fun compareTo(other: Point2D): Int {
        return if (this.y == other.y) {
            if (this.x < other.x) {
                -1
            } else {
                1
            }
        } else if (this.y < other.y) {
            -1
        } else {
            1
        }
    }

    fun generateAdjacent(): List<Point2D> = listOf(
        this.copy(x = this.x - 1),
        this.copy(x = this.x + 1),
        this.copy(y = this.y - 1),
        this.copy(y = this.y + 1)
    )
}
