package com.tompietri.aoc2022.day06

import com.tompietri.utils.readInputWithSingleLine
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day6test {
    @Test
    fun `First solution - My input should return 68775`() {
        val input = readInputWithSingleLine(2022, 6)
        assertThat(day6FirstSolution(input)).isEqualTo(1816)
    }

    @Test
    fun `Second solution - My input should return 1215`() {
        val input = readInputWithSingleLine(2022, 6)
        assertThat(day6SecondSolution(input)).isEqualTo(2625)
    }
}