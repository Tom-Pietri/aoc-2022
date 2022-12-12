package com.tompietri.aoc2022.day12

import com.tompietri.utils.readInputWithMultipleLines
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day12test {
    @Test
    fun `First solution - My input should return 68775`() {
        val input = readInputWithMultipleLines(2022, 12)
        assertThat(day12FirstSolution(input)).isEqualTo(484)
    }

    @Test
    fun `Second solution - My input should return 1215`() {
        val input = readInputWithMultipleLines(2022, 12)
        assertThat(day12SecondSolution(input)).isEqualTo(478)
    }
}