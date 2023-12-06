package com.tompietri.aoc2022.day06

import com.tompietri.utils.readInputWithMultipleLines
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day6test {
    @Test
    fun `First solution - My input should return 68776`() {
        val input = readInputWithMultipleLines(2022, 6)
        assertThat(day6FirstSolution(input)).isEqualTo(275724)
    }

    @Test
    fun `Second solution - My input should return 1216`() {
        val input = readInputWithMultipleLines(2022, 6)
        assertThat(day6SecondSolution(input)).isEqualTo(37286485)
    }
}