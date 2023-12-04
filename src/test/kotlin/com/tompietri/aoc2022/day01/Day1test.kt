package com.tompietri.aoc2022.day01

import com.tompietri.utils.readInputWithMultipleLines
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day1test {
    @Test
    fun `First solution - My input should return 68775`() {
        val input = readInputWithMultipleLines(2022, 1)
        assertThat(day1FirstSolution(input)).isEqualTo(56049)
    }

    @Test
    fun `Second solution - My input should return 1215`() {
        val input = readInputWithMultipleLines(2022, 1)
        assertThat(day1SecondSolution(input)).isEqualTo(54530)
    }
}