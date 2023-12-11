package com.tompietri.aoc2022.day08

import com.tompietri.utils.readInputWithMultipleLines
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day8test {
    @Test
    fun `First solution`() {
        val input = readInputWithMultipleLines(2022, 8)
        assertThat(day8FirstSolution(input)).isEqualTo(20569)
    }

    @Test
    fun `Second solution`() {
        val input = readInputWithMultipleLines(2022, 8)
        assertThat(day8SecondSolution(input)).isEqualTo(244848488)
    }
}