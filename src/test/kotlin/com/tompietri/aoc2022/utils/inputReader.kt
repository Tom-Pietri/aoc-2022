package com.tompietri.utils

import java.io.File

fun getInputFileForDay(dayNumber: Int, year: Int): File {
    return File("./src/main/kotlin/com/tompietri/aoc$year/day${dayNumber.toString().padStart(2, '0')}/day$dayNumber.input")
}

fun readInputWithSingleLine(year: Int, dayNumber: Int): String {
    return getInputFileForDay(dayNumber, year).readLines(Charsets.UTF_8)[0]
}

fun readInputWithMultipleLines(year: Int, dayNumber: Int): List<String> {
    return getInputFileForDay(dayNumber, year).readLines(Charsets.UTF_8)
}

fun readSingleNumberInput(year: Int, dayNumber: Int): Int {
    return readInputWithSingleLine(year, dayNumber).toInt()
}

fun readMultipleNumberInput(year: Int, dayNumber: Int): List<Int> {
    return readInputWithMultipleLines(year, dayNumber).map { it.toInt() }
}

fun readMultipleNumberInputSingleLine(year: Int, dayNumber: Int, delimiter: String = ","): List<Int> {
    return readInputWithSingleLine(year, dayNumber).split(delimiter).map { it.toInt() }
}
