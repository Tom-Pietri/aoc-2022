package com.tompietri.aoc2022.day07

import com.tompietri.utils.readInputWithMultipleLines
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.math.BigInteger

class Day7test {
    // This one is broken with the changes made for p2
    @Test
    fun `First solution`() {
        val input = readInputWithMultipleLines(2022, 7)
        assertThat(day7FirstSolution(input)).isEqualTo(246409899)
    }

    @Test
    fun `Second solution`() {
        val input = readInputWithMultipleLines(2022, 7)
        assertThat(day7SecondSolution(input)).isEqualTo(244848487)
    }
}