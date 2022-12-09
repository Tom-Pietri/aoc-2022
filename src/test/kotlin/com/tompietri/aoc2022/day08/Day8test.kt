package com.tompietri.aoc2022.day08

import com.tompietri.utils.readInputWithMultipleLines
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day8test {
    @Test
    fun `First solution - My input should return 68775`() {
        val input = readInputWithMultipleLines(2022, 8)
        assertThat(day8FirstSolution(input)).isEqualTo(1789)
    }

    @Test
    fun `Second solution - My input should return 1215`() {
        val input = readInputWithMultipleLines(2022, 8)
        assertThat(day8SecondSolution(input)).isEqualTo(314820)
    }
}