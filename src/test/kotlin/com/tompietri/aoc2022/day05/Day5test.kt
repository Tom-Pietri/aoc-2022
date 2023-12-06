package com.tompietri.aoc2022.day05

import com.tompietri.utils.readInputWithMultipleLines
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day5test {
    @Test
    fun `First solution - My input should return 68775`() {
        val input = readInputWithMultipleLines(2022, 5)
        assertThat(day5FirstSolution(input)).isEqualTo(26218)
    }

    @Test
    fun `Second solution - My input should return 1215`() {
        val input = readInputWithMultipleLines(2022, 5)
        assertThat(day5SecondSolution(input)).isEqualTo(823)
    }
}