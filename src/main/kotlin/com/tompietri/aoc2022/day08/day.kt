package com.tompietri.aoc2022.day08

import java.math.BigInteger

fun day8FirstSolution(input: List<String>): Int {
    val instructions = input[0]

    val nodesDictionary = input.drop(2)
        .map { parseNode(it) }
        .associateBy { it.name }
    var currentNode = nodesDictionary["AAA"]!!
    var nbSteps = 0
    while (currentNode.name != "ZZZ") {
        val instruction = instructions[nbSteps % instructions.length]
        if (instruction == 'L') {
            currentNode = nodesDictionary[currentNode.left]!!
        } else {
            currentNode = nodesDictionary[currentNode.right]!!
        }
        nbSteps++
    }
    return nbSteps
}

fun parseNode(line: String): Node {
    val regex = Regex("(.{3}) = \\((.{3}), (.{3})\\)")
    val matches = regex.matchEntire(line)!!

    return Node(matches.groupValues[1], matches.groupValues[2], matches.groupValues[3])
}


fun day8SecondSolution(input: List<String>): BigInteger {
    val instructions = input[0]

    val nodes = input.drop(2)
        .map { parseNode(it) }
    val nodesDictionary = nodes.associateBy { it.name }
    val currentNodes = nodes.filter { it.name.last() == 'A' }

    val pathToStartNodes = mutableListOf<PathAndSteps>()
    currentNodes.forEach { startNode ->
        val pathsToStartNode = mutableListOf<PathAndSteps>()
        while (true) {
            val path = getPathToEndNode(startNode, instructions, nodesDictionary)
            if (pathsToStartNode.contains(path)) {
                pathToStartNodes.add(path)
                break
            }
            pathsToStartNode.add(path)
        }
    }

    return pathToStartNodes.map { it.nbSteps.toBigInteger() }
        .reduce { acc, bigInteger -> lcm(acc, bigInteger) }
}

private fun getPathToEndNode(
    startNode: Node,
    instructions: String,
    nodesDictionary: Map<String, Node>
): PathAndSteps {
    var currentNode = startNode
    var nbSteps = 0
    var path = ""
    while (currentNode.name.last() != 'Z') {
        val instruction = instructions[nbSteps % instructions.length]
        path += instruction
        currentNode = if (instruction == 'L') {
            nodesDictionary[currentNode.left]!!
        } else {
            nodesDictionary[currentNode.right]!!
        }
        nbSteps++
    }

    return PathAndSteps(path, nbSteps)
}

private fun gcd(a: BigInteger, b: BigInteger): BigInteger {
    if (b == BigInteger.ZERO) {
        return a
    }
    return gcd(b, a % b)
}

private fun lcm(first: BigInteger, second: BigInteger): BigInteger {
    return (first / gcd(first, second)) * second
}


data class PathAndSteps(val path: String, val nbSteps: Int)

data class Node(val name: String, val left: String, val right: String)