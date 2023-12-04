package com.tompietri.aoc2022.day03

import com.tompietri.utils.readInputWithMultipleLines
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day3test {
    @Test
    fun `First solution - My input should return 68775`() {
        val input = readInputWithMultipleLines(2022, 3)
        assertThat(day3FirstSolution(input)).isEqualTo(521601)
    }

    @Test
    fun `Second solution - My input should return 1215`() {
        val input = readInputWithMultipleLines(2022, 3)
        assertThat(day3SecondSolution(input)).isEqualTo(467835)
    }
}