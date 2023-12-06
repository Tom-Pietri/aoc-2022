package com.tompietri.aoc2022.day04

import kotlin.math.pow

fun day4FirstSolution(input: List<String>): Int {
    val tickets = mapTickets(input)

    return tickets.sumOf { it.score() }
}

private fun mapTickets(input: List<String>): List<Ticket> {
    val tickets = input.mapIndexed { index, line ->
        val (winningNumbers, myNumbers) = line.split(":")[1]
            .split("|")
            .map { numbers -> numbers.trim().split(" ").filter { it != "" }.map { it.toInt() } }
        Ticket(index, winningNumbers.toSet(), myNumbers.toSet())
    }
    return tickets
}


fun day4SecondSolution(input: List<String>): Int {
    val ticketsCopy = mapTickets(input)
    val tickets = ticketsCopy.toMutableList()
    var count = 0;
    while (tickets.isNotEmpty()) {
        count++
        val ticket = tickets.removeLast()
        val matches = ticket.nbMatch()
        for (i in 1..matches) {
            tickets.add(ticketsCopy[ticket.index + i])
        }
    }

    return count
}

private data class Ticket(val index: Int, val winningNumbers: Set<Int>, val myNumbers: Set<Int>) {
    fun nbMatch(): Int = winningNumbers.count { myNumbers.contains(it) }
    fun score(): Int {
        val count = winningNumbers.count { myNumbers.contains(it) }
        if (count == 0) return 0

        return 2.0.pow(count - 1).toInt()
    }
}