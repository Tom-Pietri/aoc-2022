package com.tompietri.aoc2022.day01

fun day1FirstSolution(input: List<String>): Int {
    return input.sumOf {
        var firstNum: Char? = null
        var lastNum: Char? = null
        it.forEach { c ->
            if (c.isDigit()) {
                if (firstNum == null) {
                    firstNum = c
                }
                lastNum = c
            }
        }
        val value = "$firstNum$lastNum"
        value.toInt()
    }
}


fun day1SecondSolution(input: List<String>): Int {

    return input.sumOf {
        toNumbers(it)
    }
}

val numbers = listOf(
    "one" to '1',
    "two" to '2',
    "three" to '3',
    "four" to '4',
    "five" to '5',
    "six" to '6',
    "seven" to '7',
    "eight" to '8',
    "nine" to '9'
)

fun toNumbers(line: String): Int {
    val numberIndex = numbers.map {
        Truc(
            line.indexOf(it.first),
//            line.lastIndexOf(it.first).let { index -> if (index > 0) index + it.first.length else index },
            line.lastIndexOf(it.first),
            it.second
        )
    }
    val minNumber = numberIndex.filter { it.firstIndex >= 0 }
        .minByOrNull { it.firstIndex }
    val maxNumber = numberIndex.filter { it.lastIndex >= 0 }
        .maxByOrNull { it.lastIndex }

    var firstNum = minNumber?.let { it.firstIndex to it.number }
    var lastNum = maxNumber?.let { it.lastIndex to it.number }
    line.forEachIndexed { index, c->
        if (c.isDigit()) {
            if (firstNum == null || firstNum!!.first > index) {
                firstNum = index to c
            }
            if (lastNum == null || lastNum!!.first < index) {
                lastNum = index to c
            }
        }
    }

    val value = "${firstNum!!.second}${lastNum!!.second}"
    return value.toInt()
}

private data class Truc(val firstIndex: Int, val lastIndex: Int, val number: Char);