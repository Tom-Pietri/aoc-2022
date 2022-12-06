package com.tompietri.aoc2022.day01

fun day1FirstSolution(input: List<String>): Int = takeMaxCalories(input, 1)
fun day1SecondSolution(input: List<String>): Int = takeMaxCalories(input, 3)

private fun takeMaxCalories(input: List<String>, nbToTake: Int) = parseInput(input)
    .map { it.map(String::toInt).sum() }
    .sorted()
    .takeLast(nbToTake)
    .sum()

private fun parseInput(input: List<String>) = input
    .joinToString(",")
    .split(",,")
    .map { it.split(",") }
