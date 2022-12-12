package com.tompietri.aoc2022.day11

import java.math.BigInteger

fun day11FirstSolution(input: List<String>): BigInteger {
    val monkeys = parseMonkeys(input).associateBy { it.id }

    repeat(20) {
        monkeys.values.forEach { monkey ->
            monkey.nbInspectedItems += monkey.items.count<BigInteger>()
            while (monkey.items.isNotEmpty()) {
                val item = monkey.items.removeFirst()
                var nextWorryLevel = monkey.operation.apply(item).divide(BigInteger.valueOf(3))
                if (nextWorryLevel.mod(monkey.testValue) == BigInteger.ZERO) {
                    monkeys[monkey.targetTrue]!!.items.add(nextWorryLevel)
                } else {
                    monkeys[monkey.targetFalse]!!.items.add(nextWorryLevel)
                }
            }
        }
    }
    val (first, second) = monkeys.values.sortedBy { it.nbInspectedItems }.takeLast(2)
    return first.nbInspectedItems.toBigInteger() * second.nbInspectedItems.toBigInteger()
}

fun day11SecondSolution(input: List<String>): BigInteger {
    val monkeys = parseMonkeys(input).associateBy { it.id }
    val ppcd = monkeys.values.fold(BigInteger.ONE) { acc, it -> acc * it.testValue }
    repeat(10000) {
        monkeys.values.forEach { monkey ->
            monkey.nbInspectedItems += monkey.items.count<BigInteger>()
            while (monkey.items.isNotEmpty()) {
                val item = monkey.items.removeFirst()
                var nextWorryLevel = monkey.operation.apply(item).mod(ppcd)
                if (nextWorryLevel.mod(monkey.testValue) == BigInteger.ZERO) {
                    monkeys[monkey.targetTrue]!!.items.add(nextWorryLevel)
                } else {
                    monkeys[monkey.targetFalse]!!.items.add(nextWorryLevel)
                }
            }
        }
    }
    val (first, second) = monkeys.values.sortedBy { it.nbInspectedItems }.takeLast(2)
    return first.nbInspectedItems.toBigInteger() * second.nbInspectedItems.toBigInteger()
}


private fun parseMonkeys(input: List<String>): List<Monkey> {
    return input.joinToString(";")
        .split(";;")
        .map { it.split(";") }
        .map {
            val id = it[0].split(" ")[1].dropLast(1).toInt()
            val items = it[1].split(":")[1].trim().split(",").map { n -> n.trim().toBigInteger() }.toMutableList()
            val operationDefinition = it[2].split("=")[1].trim().split(" ")
            val operationType = if (operationDefinition[1] == "*") {
                OperationType.TIMES
            } else {
                OperationType.PLUS
            }
            val operation = if (operationDefinition[2] == "old") {
                SelfOperation(operationType)
            } else {
                ValueOperation(operationType, it[2].split(" ").last().toBigInteger())
            }

            val testValue = it[3].split(" ").last().toBigInteger()
            val targetTrue = it[4].split(" ").last().toInt()
            val targetFalse = it[5].split(" ").last().toInt()

            Monkey(id, items, operation, testValue, targetTrue, targetFalse)
        }
}

private data class Monkey(
    val id: Int,
    val items: MutableList<BigInteger>,
    val operation: Operation,
    val testValue: BigInteger,
    val targetTrue: Int,
    val targetFalse: Int,
    var nbInspectedItems: Int = 0
)

private interface Operation {
    fun apply(old: BigInteger): BigInteger
}

private data class ValueOperation(val type: OperationType, val value: BigInteger) : Operation {
    override fun apply(old: BigInteger) = type.apply(old, value)
}

private data class SelfOperation(val operation: OperationType) : Operation {
    override fun apply(old: BigInteger) = operation.apply(old, old)
}

private enum class OperationType {
    PLUS {
        override fun apply(left: BigInteger, right: BigInteger) = left + right
    },
    TIMES {
        override fun apply(left: BigInteger, right: BigInteger) = left * right
    };

    abstract fun apply(left: BigInteger, right: BigInteger): BigInteger
}