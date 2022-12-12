package com.tompietri.aoc2022.day10

fun day10FirstSolution(input: List<String>): Int {
    var accumulator = 1
    var total = 0
    var cycleCount = 0
    input.map { it.toInstruction() }
        .flatMap { it.toSimplifiedInstruction() }
        .forEach { instruction ->
            cycleCount++
            if (cycleCount % 40 == 20) {
                total += cycleCount * accumulator
            }
            if (instruction is AddInstruction) {
                accumulator += instruction.value
            }
        }

    return total
}

private fun Instruction.toSimplifiedInstruction(): List<Instruction> {
    return if (this is AddInstruction) {
        listOf(Noop(), this)
    } else {
        listOf(this)
    }
}

fun day10SecondSolution(input: List<String>): String {
    var accumulator = 1
    var cycleCount = 0
    input.map { it.toInstruction() }
        .flatMap { it.toSimplifiedInstruction() }
        .forEach { instruction ->
            cycleCount++

            val accRange = (accumulator - 1)..(accumulator + 1)
            if (accRange.contains((cycleCount % 40) - 1)) {
                print("#")
            } else {
                print(".")
            }

            if (cycleCount % 40 == 0) {
                println()
            }

            if (instruction is AddInstruction) {
                accumulator += instruction.value
            }
        }

    return "EZFCHJAB"
}

private interface Instruction

private data class AddInstruction(val value: Int) : Instruction
private class Noop : Instruction

private fun String.toInstruction(): Instruction {
    return if (this.startsWith("noop")) {
        Noop()
    } else {
        AddInstruction(this.split(" ")[1].toInt())
    }
}