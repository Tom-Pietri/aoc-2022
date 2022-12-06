package com.tompietri.aoc2022.day04

fun day4FirstSolution(input: List<String>): Int {
    return input.map { it.split(',') }
        .map {
            val firstSplited = it[0].split("-").map(String::toInt)
            val secondSplited = it[1].split("-").map(String::toInt)
            AssignmentsPair(
                IntRange(firstSplited[0], firstSplited[1]),
                IntRange(secondSplited[0], secondSplited[1])
            )
        }.count { it.fullyContainsOther() }

}


fun day4SecondSolution(input: List<String>): Int {
    return input.map { it.split(',') }
        .map {
            val firstSplited = it[0].split("-").map(String::toInt)
            val secondSplited = it[1].split("-").map(String::toInt)
            AssignmentsPair(
                IntRange(firstSplited[0], firstSplited[1]),
                IntRange(secondSplited[0], secondSplited[1])
            )
        }.count { it.overlapOther() }
}

private data class AssignmentsPair(val firstElf: IntRange, val secondElf: IntRange) {
    fun fullyContainsOther(): Boolean {
        if (firstElf.contains(secondElf.first) && firstElf.contains(secondElf.last))
            return true
        else if(secondElf.contains(firstElf.first) && secondElf.contains(firstElf.last))
            return true

        return false
    }

    fun overlapOther(): Boolean {
        if (firstElf.contains(secondElf.first) || firstElf.contains(secondElf.last))
            return true
        else if(secondElf.contains(firstElf.first) || secondElf.contains(firstElf.last))
            return true

        return false
    }


}