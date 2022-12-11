package com.tompietri.aoc2022.day10

import com.tompietri.utils.readInputWithMultipleLines
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day10test {
    @Test
    fun `First solution - My input should return 68775`() {
        val input = readInputWithMultipleLines(2022, 10)
        assertThat(day10FirstSolution(input)).isEqualTo(13180)
    }

    @Test
    fun `Second solution - My input should return 1215`() {
        val input = readInputWithMultipleLines(2022, 10)
        assertThat(day10SecondSolution(input)).isEqualTo("EZFCHJAB")
    }
}