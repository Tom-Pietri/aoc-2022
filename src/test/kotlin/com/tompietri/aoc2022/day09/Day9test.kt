package com.tompietri.aoc2022.day09

import com.tompietri.utils.readInputWithMultipleLines
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day9test {
    @Test
    fun `First solution - My input should return 68775`() {
        val input = readInputWithMultipleLines(2022, 9)
        assertThat(day9FirstSolution(input)).isEqualTo(5981)
    }

    @Test
    fun `Second solution - My input should return 1215`() {
        val input = readInputWithMultipleLines(2022, 9)
        assertThat(day9SecondSolution(input)).isEqualTo(36)
    }
}