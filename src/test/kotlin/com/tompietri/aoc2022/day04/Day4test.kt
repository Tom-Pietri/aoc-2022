package com.tompietri.aoc2022.day04

import com.tompietri.utils.readInputWithMultipleLines
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day4test {
    @Test
    fun `First solution - My input should return 68775`() {
        val input = readInputWithMultipleLines(2022, 4)
        assertThat(day4FirstSolution(input)).isEqualTo(26218)
    }

    @Test
    fun `Second solution - My input should return 1215`() {
        val input = readInputWithMultipleLines(2022, 4)
        assertThat(day4SecondSolution(input)).isEqualTo(823)
    }
}