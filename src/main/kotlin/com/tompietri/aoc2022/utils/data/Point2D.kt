package com.tompietri.aoc2022.utils.data

data class Point2D(val x: Int, val y: Int) : Comparable<Point2D> {
    override fun compareTo(other: Point2D): Int {
        return if(this.y == other.y) {
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
}
