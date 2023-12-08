package com.tompietri.aoc2022.day07

import java.math.BigInteger

fun day7FirstSolution(input: List<String>): BigInteger {
    val hands = input.map { it.split(" ") }
        .map { Hand(chars(it[0]), it[1].toBigInteger()) }

    val groupedByType = hands.map { it to it.type }
        .groupBy { it.second }

    val result = groupedByType.entries
        .sortedBy { it.key }
        .flatMap { handGroup -> handGroup.value.sortedBy { it.first } }

    return result
        .mapIndexed { rank, pair -> pair.first.bid * (rank.toBigInteger() + BigInteger.ONE) }
        .sumOf { it }
}


fun day7SecondSolution(input: List<String>): BigInteger {
    val hands = input.map { it.split(" ") }
        .map { Hand(chars(it[0]), it[1].toBigInteger()) }

    val groupedByType = hands
        .map { it to it.getBestHandTypeWithJoker() }
        .groupBy { it.second }

    val result = groupedByType.entries
        .sortedBy { it.key }
        .flatMap { handGroup -> handGroup.value.sortedBy { it.first } }

    return result
        .mapIndexed { rank, pair -> pair.first.bid * (rank.toBigInteger() + BigInteger.ONE) }
        .sumOf { it }
}

private fun chars(cards: String) = cards.map { dictionary[it]!! }
data class Hand(val cards: List<Card>, val bid: BigInteger) : Comparable<Hand> {
    private val groupedByType = cards.groupBy { it.type }

    val type = getHandType()
    private val nbJokers = cards.count { it == Card.joker }

    public fun getBestHandTypeWithJoker(): HandType {
        return bestHandWithJoker(this, emptyList(), nbJokers).first
    }

    private fun bestHandWithJoker(hand: Hand, cardsFromJoker: List<Card>, nbJokersRemaining: Int): Pair<HandType, List<Card>?> {
        if (nbJokersRemaining > 0) {
            val cardsWithoutJoker = hand.cards.minus(Card.joker)

            val generatedHands = dictionary.values.map { cardReplaced ->
                val nextCardsFromJoker = cardsFromJoker + cardReplaced
                bestHandWithJoker(Hand(cardsWithoutJoker, hand.bid), nextCardsFromJoker, nbJokersRemaining - 1)
            }

            return generatedHands.map { it.first }.max() to null
        }

        return Hand(hand.cards + cardsFromJoker, hand.bid).type to hand.cards + cardsFromJoker
    }


    override fun toString(): String {
        return cards.map { reverseDictionary[it.type] }.joinToString("") + " - ${this.bid}"
    }

    private fun isFiveOfAKind() = groupedByType.values.any { it.size == 5 }
    private fun isFourOfAKind() = groupedByType.values.any { it.size == 4 }
    private fun isFullHouse(): Boolean {
        val grouped = groupedByType
        return grouped.values.count() == 2
                && grouped.values.any { it.size == 3 }
                && grouped.values.any { it.size == 2 }
    }

    private fun isThreeOfAKind() = groupedByType.values.any { it.size == 3 }
    private fun isTwoPairs() = groupedByType.values.count { it.size == 2 } == 2
    private fun isOnePair() = groupedByType.values.any { it.size == 2 }


    private fun getHandType(): HandType {
        return when {
            isFiveOfAKind() -> HandType.FiveOfAKind
            isFourOfAKind() -> HandType.FourOfAKind
            isFullHouse() -> HandType.FullHouse
            isThreeOfAKind() -> HandType.ThreeOfAKind
            isTwoPairs() -> HandType.TwoPairs
            isOnePair() -> HandType.OnePair
            else -> HandType.HighestCard
        }
    }

    override fun compareTo(other: Hand): Int {
        other.cards.zip(this.cards)
            .map { it.second.type.compareTo(it.first.type) }
            .forEach {
                if (it != 0) {
                    return it
                }
            }
        return 0
    }
}

enum class HandType(val value: Int) {
    HighestCard(1),
    OnePair(2),
    TwoPairs(3),
    ThreeOfAKind(4),
    FullHouse(5),
    FourOfAKind(6),
    FiveOfAKind(7),
}

data class Card(val type: Int) {
    companion object {
        val joker = Card(1)
    }
}

val dictionary = mapOf(
    'A' to Card(14),
    'K' to Card(13),
    'Q' to Card(12),
    'T' to Card(10),
    '9' to Card(9),
    '8' to Card(8),
    '7' to Card(7),
    '6' to Card(6),
    '5' to Card(5),
    '4' to Card(4),
    '3' to Card(3),
    '2' to Card(2),
    'J' to Card(1),
)
val reverseDictionary = mapOf(
    14 to 'A',
    13 to 'K',
    12 to 'Q',
    10 to 'T',
    9 to '9',
    8 to '8',
    7 to '7',
    6 to '6',
    5 to '5',
    4 to '4',
    3 to '3',
    2 to '2',
    1 to 'J',
)