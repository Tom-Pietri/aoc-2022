package com.tompietri.aoc2022.day11

import com.tompietri.utils.readInputWithMultipleLines
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.math.BigInteger

class Day11test {
    @Test
    fun `First solution - My input should return 68775`() {
        val input = readInputWithMultipleLines(2022, 11)
        assertThat(day11FirstSolution(input)).isEqualTo("100345".toBigInteger())
    }

    @Test
    fun `Second solution - My input should return 1215`() {
        val input = readInputWithMultipleLines(2022, 11)
        assertThat(day11SecondSolution(input)).isEqualTo("28537348205".toBigInteger())
    }
}