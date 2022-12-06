package com.tompietri.aoc2022.day05

fun day5FirstSolution(input: List<String>): String {
    val parsedInput = parsedInputPair(input)

    parsedInput.moves.forEach { move ->
        val moved = parsedInput.columns[move.sourceColumn - 1].takeLast(move.nbToMove)
        parsedInput.columns[move.targetColumn - 1].addAll(moved.reversed())
        repeat(move.nbToMove) {
            parsedInput.columns[move.sourceColumn - 1].removeLast()
        }
    }

    return parsedInput.columns.map { it.last() }.joinToString("")
}


fun day5SecondSolution(input: List<String>): String {
    val parsedInput = parsedInputPair(input)

    parsedInput.moves.forEach { move ->
        val moved = parsedInput.columns[move.sourceColumn - 1].takeLast(move.nbToMove)
        parsedInput.columns[move.targetColumn - 1].addAll(moved)
        repeat(move.nbToMove) {
            parsedInput.columns[move.sourceColumn - 1].removeLast()
        }
    }

    return parsedInput.columns.map { it.last() }.joinToString("")
}

private fun parsedInputPair(input: List<String>): ParsedInput {
    val inputParts = input.joinToString(",").split(",,")
    val initialStateInput = inputParts[0].split(",")
    val nbColumns = initialStateInput.last().trim().split(" ").last().toInt()
    val rows = initialStateInput.dropLast(1).reversed()
        .map { it.chunked(4).map { c -> c[1] }.toMutableList() }
    val columns = mutableListOf<MutableList<Char>>()
    repeat(nbColumns) { i ->
        columns.add(mutableListOf())
        rows.forEach { row -> row.getOrNull(i)?.let { rowValue -> columns[i].add(rowValue) } }
    }
    columns.forEach { col -> col.removeIf { it == ' ' } }

    val regex = Regex("move (\\d*) from (\\d*) to (\\d*)")
    val moveInput = inputParts[1].split(",")
        .map { regex.find(it)!!.groupValues }
        .map { Move(it[1].toInt(), it[2].toInt(), it[3].toInt()) }
    return ParsedInput(columns, moveInput)
}

data class ParsedInput(val columns: List<MutableList<Char>>, val moves: List<Move>)
data class Move(val nbToMove: Int, val sourceColumn: Int, val targetColumn: Int)